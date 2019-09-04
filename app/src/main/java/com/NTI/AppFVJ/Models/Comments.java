package com.NTI.AppFVJ.Models;

public class Comments {
    private int Id;
    private int Leads_Id;
    private int Users_Id;
    private String Text;
    private String TimeStamp;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getLeads_Id() {
        return Leads_Id;
    }

    public void setLeads_Id(int leads_Id) {
        Leads_Id = leads_Id;
    }

    public int getUsers_Id() {
        return Users_Id;
    }

    public void setUsers_Id(int users_Id) {
        Users_Id = users_Id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }
}
