package com.example.atod.pizzavendor;

public class User {

    String name;
    String userName;
    String password;
    String creditCard;
    String address;

    public User(String n, String un, String p, String cc, String a){

        name = n;
        userName = un;
        password = p;
        creditCard = cc;
        address = a;

    }

    public User(String un, String p){
        userName = un;
        password = p;
    }
}
