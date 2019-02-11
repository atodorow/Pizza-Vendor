package com.example.atod.pizzavendor;

import android.provider.BaseColumns;

public class TableData {


    public TableData(){



    }

    public static abstract class TableInfo implements BaseColumns
    {
        public static final String Name = "Name";
        public static final String Username= "Username";
        public static final String Password= "Password";
        public static final String Address= "Address";
        public static final String CreditCard= "CreditCard";
        public static final String Phone_Number= "Phone_Number";
        public static final String TableName= "Pizza_Vendor";
        public static final String DBName = "Pizza_Vendor.db";

    }
}
