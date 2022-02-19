package com.example.finalproject.Bills.RecyclerView;

public class Model {
    String Status,Name,Date,Time,Price;

    public Model(String status, String name, String date, String time, String price) {
        Status = status;
        Name = name;
        Date = date;
        Time = time;
        Price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
