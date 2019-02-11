package com.example.atod.pizzavendor;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class CustomerInfo extends AppCompatActivity {

    public static String userData;
    public static final String data = "data";
    public static String UserN;
    public static String Password;
    public static String Name;
    public static String Address;
    public static String Credit;
    public static String Phone;
    public static String web = "https://www.pizzeriavetri.com/menu";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);


        EditText name =  findViewById(R.id.nameText);
        EditText user = findViewById(R.id.usernameText);
        EditText pass = findViewById(R.id.passwordText);
        EditText address = findViewById(R.id.addressText);
        EditText credit = findViewById(R.id.creditCardText);
        EditText phone = findViewById(R.id.phoneNumberText);

        Toolbar tool = findViewById(R.id.tool);

        setActionBar(tool);


        userData = getIntent().getStringExtra(Home.data);

        String [] t = userData.split(",");

        name.setText(t[0]);
        user.setText(t[1]);
        pass.setText(t[2]);
        address.setText(t[3]);
        credit.setText(t[4]);
        phone.setText(t[5]);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id){
            case R.id.action_home:
                Intent intent = new Intent(this, Home.class);
                intent.putExtra(data, userData);
                startActivity(intent);

                return true;

            case R.id.action_web:
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(web));

                if(webIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(webIntent);
                }

                return true;

            case R.id.action_logOut:

                System.exit(0);

                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onClickOK(View view) {

        EditText name =  findViewById(R.id.nameText);
        EditText user = findViewById(R.id.usernameText);
        EditText pass = findViewById(R.id.passwordText);
        EditText address = findViewById(R.id.addressText);
        EditText credit = findViewById(R.id.creditCardText);
        EditText phone = findViewById(R.id.phoneNumberText);


        Name = name.getText().toString();
        UserN = user.getText().toString();
        Password = pass.getText().toString();
        Address = address.getText().toString();
        Credit = credit.getText().toString();
        Phone = phone.getText().toString();

        DatabaseHelper db = new DatabaseHelper(this);

        Boolean add = db.addData(Name, UserN, Password, Address, Credit, Phone);

        if(add){
            Toast.makeText(CustomerInfo.this, "New user added!", Toast.LENGTH_LONG).show();
        }

        else{

            Toast.makeText(CustomerInfo.this, "Invalid entry", Toast.LENGTH_LONG).show();
        }


    }
}
