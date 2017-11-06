package com.example.matemo.week10app;

/**
 * Created by Matemo on 10/17/2017.
 */

public class Contact {
    private int id;
    private String name, phone;

//    public Contact(int id, String name, String phone)
//    {
//        this.id = id;
//        this.name = name;
//        this.phone = phone;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return id+". " + name + "\n" + phone;
    }
}
