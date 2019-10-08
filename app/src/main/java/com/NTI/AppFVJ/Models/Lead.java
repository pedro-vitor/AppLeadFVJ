package com.NTI.AppFVJ.Models;

public class Lead {
    private int Id;
    private int ExternId;
    private int UserId;
    private String Name;
    private String Email;
    private String NumberPhone;
    private String DesiredCourse;
    private String Town;
    private String Address;
    private String CreatedAt;
    private int Active;
    private int Updated;


    public Lead() {}
    public Lead(int Id, int ExternId, int UserId, String Name, String Email, String NumberPhone, String DesiredCourse, String Town, String Address, String CreatedAt, int Active, int Updated) {
        this.Id = Id;
        this.ExternId = ExternId;
        this.UserId = UserId;
        this.Name = Name;
        this.Email = Email;
        this.NumberPhone = NumberPhone;
        this.DesiredCourse = DesiredCourse;
        this.Town = Town;
        this.Address = Address;
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

    public int getUserId() {
        return this.UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNumberPhone() {
        return this.NumberPhone;
    }

    public void setNumberPhone(String NumberPhone) {
        this.NumberPhone = NumberPhone;
    }

    public String getDesiredCourse() {
        return this.DesiredCourse;
    }

    public void setDesiredCourse(String DesiredCourse) {
        this.DesiredCourse = DesiredCourse;
    }

    public String getTown() {
        return this.Town;
    }

    public void setTown(String Town) {
        this.Town = Town;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCreatedAt() {
        return this.CreatedAt;
    }

    public void setCreatedAt(String CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
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
