package com.example.atod.pizzavendor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;


public class Checkout extends AppCompatActivity {

    public static String data;
    public static String bevData;
    public static ArrayList<String> array = new ArrayList<String>();
    public static String type;
    public static String size;
    public static final String Order = "Order";
    public static String name;
    public static String address;
    public static String phone;
    public static String credit;
    public static String totalOrder;
    public static String email = "a.tod@comcast.net";
    public static String userData;
    public static final String user = "user";

    private String merchantKey, userCredentials;

    // These will hold all the payment parameters


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);



        TextView Name = findViewById(R.id.name);
        TextView username = findViewById(R.id.username);



         data = getIntent().getStringExtra(Pizzas.dataTitle);
         bevData= getIntent().getStringExtra(Beverages.order);
         userData = getIntent().getStringExtra(Pizzas.userInfo);

        EditText nameTxt = findViewById(R.id.nameText);
        EditText userTxt = findViewById(R.id.usernameText);
        EditText passTxt = findViewById(R.id.passwordText);
        EditText addressTxt = findViewById(R.id.addressText);
        EditText phoneTxt = findViewById(R.id.phoneNumberText);
        EditText creditTxt = findViewById(R.id.creditCardText);

        if (userData != null) {
            String [] t = userData.split(",");

            nameTxt.setText(t[0]);
            userTxt.setText(t[1]);
            passTxt.setText(t[2]);
            addressTxt.setText(t[3]);
            creditTxt.setText(t[4]);
            phoneTxt.setText(t[5]);
        }

        //name.setText(array[0]);


    }




    public void onClickCart(View view) {

        if(bevData!=null){


            EditText nameTxt = findViewById(R.id.nameText);
            name= nameTxt.getText().toString();

            EditText addressTxt = findViewById(R.id.addressText);
            address = addressTxt.getText().toString();

            EditText phoneTxt = findViewById(R.id.phoneNumberText);
            phone= phoneTxt.getText().toString();

            EditText creditTxt = findViewById(R.id.creditCardText);
            credit = creditTxt.getText().toString();

            Intent intent = new Intent(this, Cart.class);
            //String order = name +","+address+","+phone+","+credit + "," + bevData;
            intent.putExtra(Order,bevData);

            startActivity(intent);
        }

        else{

            EditText nameTxt = findViewById(R.id.nameText);
            name= nameTxt.getText().toString();

            EditText addressTxt = findViewById(R.id.addressText);
            address = addressTxt.getText().toString();

            EditText phoneTxt = findViewById(R.id.phoneNumberText);
            phone= phoneTxt.getText().toString();

            EditText creditTxt = findViewById(R.id.creditCardText);
            credit = creditTxt.getText().toString();

            Intent intent = new Intent(this, Cart.class);
            String order = name +","+address+","+phone+","+credit + "," + data;
            intent.putExtra(Order,order);

            startActivity(intent);

        }




    }

    public void onClickAdd(View view) {

        EditText nameTxt = findViewById(R.id.nameText);
        name= nameTxt.getText().toString();

        EditText addressTxt = findViewById(R.id.addressText);
        address = addressTxt.getText().toString();

        EditText phoneTxt = findViewById(R.id.phoneNumberText);
        phone= phoneTxt.getText().toString();

        EditText creditTxt = findViewById(R.id.creditCardText);
        credit = creditTxt.getText().toString();


        Intent intent = new Intent(this, Menu.class);
        String order = name +","+address+","+phone+","+credit + "," + data;
        intent.putExtra(Order,order);

        startActivity(intent);

    }
}
