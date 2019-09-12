package com.NTI.AppFVJ.Models;

public class Lead {
    private int Id;
    private int Users_Id;
    private String Name;
    private String Email;
    private String Number_phone;
    private String Desired_course;
    private String Town;
    private String Address;
    private String CreatedAt;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUsers_Id() {
        return Users_Id;
    }

    public void setUsers_Id(int users_Id) {
        Users_Id = users_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber_phone() {
        return Number_phone;
    }

    public void setNumber_phone(String number_phone) {
        Number_phone = number_phone;
    }

    public String getDesired_course() {
        return Desired_course;
    }

    public void setDesired_course(String desired_course) {
        Desired_course = desired_course;
    }

    public String getTown() {
        return Town;
    }

    public void setTown(String town) {
        Town = town;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
