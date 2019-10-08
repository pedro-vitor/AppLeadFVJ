package com.NTI.AppFVJ.Models;

public class User {
    private int Id;
    private int ExternId;
    private String Name;
    private String Email;
    private String Password;
    private String CreatedAt;
    private int Active;
    private int Updated;


    public User() {}
    public User(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
    }
    public User(int Id, int ExternId, String Name, String Email, String Password, String CreatedAt, int Active, int Updated) {
        this.Id = Id;
        this.ExternId = ExternId;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.CreatedAt = CreatedAt;
        this.Active = Active;
        this.Updated = Updated;
    }

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

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
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

    public void setUpdated(int Updated) {
        this.Updated = Updated;
    }

    public int getUpdated() {
        return this.Updated;
    }
}
