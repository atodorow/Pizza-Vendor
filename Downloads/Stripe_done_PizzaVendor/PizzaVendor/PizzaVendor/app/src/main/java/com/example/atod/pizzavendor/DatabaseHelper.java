package com.example.atod.pizzavendor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.atod.pizzavendor.TableData;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String table = "Create Table ";
    public static final String p1= " (";
    public static final String p2= ");";
    public static final int database_version = 1;
    public static final String tableCreator = "CREATE TABLE Pizza_Vendor( "
            +"Name TEXT,"
            +"Username TEXT,"
            +"Password TEXT,"
            +"Address TEXT,"
            +"CreditCard TEXT,"
            +"Phone_Number TEXT);";


    public static final String Create_Query = table + TableData.TableInfo.TableName + p1 + TableData.TableInfo.Name + " TEXT," + TableData.TableInfo.Username  + " TEXT," + TableData.TableInfo.Password + " TEXT," + TableData.TableInfo.Address + " TEXT," + TableData.TableInfo.CreditCard + " TEXT," + TableData.TableInfo.Phone_Number + " TEXT" + ");";


    public DatabaseHelper(Context context) {
        super(context, TableData.TableInfo.DBName, null, database_version);
        Log.d("Database Operations", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(tableCreator);
        Log.d("Database Operations", "Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TableData.TableInfo.TableName);
        onCreate(db);

    }

    public boolean addData(String n, String u, String p, String a, String cc, String ph ){

        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.Username, u);
        cv.put(TableData.TableInfo.Password, p);
        cv.put(TableData.TableInfo.Name, n);
        cv.put(TableData.TableInfo.Address, a);
        cv.put(TableData.TableInfo.CreditCard, cc);
        cv.put(TableData.TableInfo.Phone_Number, ph);
        long k = SQ.insert(TableData.TableInfo.TableName, null, cv);
        Log.d("Database Operations", "One raw inserted");

        //if data inserted incorrectly it will return -1.
        if (k == -1) {
            return false;
        }

        else{
            return true;
        }
    }

    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + TableData.TableInfo.TableName, null);
        return res;
    }
}
