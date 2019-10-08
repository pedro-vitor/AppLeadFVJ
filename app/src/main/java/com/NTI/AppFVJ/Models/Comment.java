package com.NTI.AppFVJ.Models;

public class Comment {
    private int Id;
    private int ExternId;
    private int LeadsId;
    private int UsersId;
    private String Text;
    private String CreatedAt;
    private int Active;
    private int Updated;


    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getExternId() {
        return this.ExternId;
    }

    public void setExternId(int ExternId) {
        this.ExternId = ExternId;
    }

    public int getLeadsId() {
        return this.LeadsId;
    }

    public void setLeadsId(int LeadsId) {
        this.LeadsId = LeadsId;
    }

    public int getUsersId() {
        return this.UsersId;
    }

    public void setUsersId(int UsersId) {
        this.UsersId = UsersId;
    }

    public String getText() {
        return this.Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public String getCreatedAt() {
        return this.CreatedAt;
    }

    public void setCreatedAt(String CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public int getActive() {
        return this.Active;
    }

    public void setActive(int Active) {
        this.Active = Active;
    }

    public int getUpdated() {
        return this.Updated;
    }

    public void setUpdated(int Updated) {
        this.Updated = Updated;
    }
}
