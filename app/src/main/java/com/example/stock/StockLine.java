package com.example.stock;



// Written by Cade Cottrell for CS4301.002, Stock Assignment
// netid: cac160030


// This is to store 1 line of data from the website
// It holds date, open, high, low, close, volume, adj close
public class StockLine {
    String Date;
    String Open;
    String High;
    String Low;
    String Close;
    String Volume;
    String AdjClose;


    public StockLine(String date, String open, String high, String low, String close, String volume, String adjClose) {
        Date = date;
        Open = open;
        High = high;
        Low = low;
        Close = close;
        Volume = volume;
        AdjClose = adjClose;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setOpen(String open) {
        Open = open;
    }

    public void setHigh(String high) {
        High = high;
    }

    public void setLow(String low) {
        Low = low;
    }

    public void setClose(String close) {
        Close = close;
    }

    public void setVolume(String volume) {
        Volume = volume;
    }

    public void setAdjClose(String adjClose) {
        AdjClose = adjClose;
    }

    public String getDate() {
        return Date;
    }

    public String getOpen() {
        return Open;
    }

    public String getHigh() {
        return High;
    }

    public String getLow() {
        return Low;
    }

    public String getClose() {
        return Close;
    }

    public String getVolume() {
        return Volume;
    }

    public String getAdjClose() {
        return AdjClose;
    }
}
