package com.example.atod.pizzavendor;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String[] dbList;
    public static boolean signIn;
    public static String userData;
    public static EditText user;
    public static EditText password;
    public static final String data = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         user = findViewById(R.id.userName);
        password = findViewById(R.id.Password);

        ImageView a = findViewById(R.id.imagePizza);

    }




    public void onClickPizza(View view) {

        Intent intent = new Intent(this, Home.class);
//        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
    }


    public void onClickSign(View view) {

        DatabaseHelper myDB = new DatabaseHelper(this);
        Cursor res = myDB.getAllData();


//        if(res.getCount() == 0){
//            Toast.makeText(this, "Contact administrator~", Toast.LENGTH_LONG).show();
//            return;
//        }


        while(res.moveToNext()){

            String u =res.getString(1);
            String p = res.getString(2);

            String userText = user.getText().toString();
            String passText = password.getText().toString();

            if(u.equalsIgnoreCase(userText) && p.equalsIgnoreCase(passText)){

                dbList = new String[6];

               dbList[0]=res.getString(0);
               dbList[1] = res.getString(1);
               dbList[2] = res.getString(2);
               dbList[3] = res.getString(3);
               dbList[4] = res.getString(4);
               dbList[5] = res.getString(5);


                userData = dbList[0] +",";
                userData += dbList[1] +",";
                userData += dbList[2] +",";
                userData += dbList[3]+",";
                userData += dbList[4]+",";
                userData += dbList[5];


            }
        }

        ///

        if(userData==null){
            Toast.makeText(MainActivity.this, "Please enter valid username and password", Toast.LENGTH_LONG).show();
        return;

        }

        else{
            Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, Home.class);


            i.putExtra(data, userData);
            startActivity(i);
        }



    }

    public void onClickUser(View view) {

        password.setText("");
    }

    public void onClickUsername(View view) {
        user.setText("");
    }
}
