package com.example.stock;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

// Written by Cade Cottrell for CS4301.002, Stock Assignment
// netid: cac160030

// AsyncTask that displays the data on the recycleview
public class StockFinder extends AsyncTask< String, String, ArrayList<StockLine> > {

    ArrayList<StockLine> list;
    private Context contextRef;
    private View rootview;
    RecyclerViewAdapter adapter;
    ProgressDialog progressDialog;


    //Constructor, needs context, the view, and the Recyclerview Adapter
    public StockFinder(Context contextRef, View rootview, RecyclerViewAdapter adapter) {
        this.contextRef = contextRef;
        this.rootview = rootview;
        this.adapter = adapter;
    }


    // doInBackground goes to the website and extracts data from it
    // then stores this data in StockLine objects returns that.
    @Override
    protected ArrayList<StockLine> doInBackground(String... strings) {

        try{
            URL url = new URL(strings[0]);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            list = new ArrayList<>();

            while(buffer.readLine() != null)
            {
                if(isCancelled())
                {
                    while(list.size() > 0)
                    {
                        list.remove(0);
                    }
                    break;
                }

                String line = buffer.readLine();
                String[] split = line.split(",");
                StockLine stockLine = new StockLine(split[0],split[1], split[2], split[3], split[4], split[5], split[6]);

                list.add(stockLine);

            }
            buffer.close();

            StockLine data = new StockLine("Date", "Open", "High", "Low", "Close", "Vol", "AdjClose");
            list.add(0, data);

            return list;

        }
        catch(Exception ex)
        {
            return list;
        }
    }


    // Notify user that it is finding the data.
    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(contextRef, "Progress", "Downloading");
        progressDialog.setCancelable(true);


        //If user cancels print a Toast to let user know it is canceled
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(contextRef, "Process Canceled", Toast.LENGTH_LONG).show();
                cancel(true);
            }
        });
    }

    // Adds the adapter to the recyclerview so the user can see the stock data
    @Override
    protected void onPostExecute(ArrayList<StockLine> a) {

        //Get everything from arraylist
        //Use it for adapter
        //put adapter in recyclerview
        //update ui.

        progressDialog.dismiss();


        //If list is null, that means website did not have any data on it, so 404 stock doesnt exist
        if(list == null)
        {
            Toast.makeText(contextRef, "That Stock Doesn't Exist", Toast.LENGTH_LONG).show();
        }
        else
        {
            adapter = new RecyclerViewAdapter(a, contextRef);

            RecyclerView recyclerView = rootview.findViewById(R.id.recycler_view);

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(contextRef));
        }


    }

}
