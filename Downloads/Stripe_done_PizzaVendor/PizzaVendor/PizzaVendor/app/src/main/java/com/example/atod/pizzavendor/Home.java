package com.example.atod.pizzavendor;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    public static String userData;
    public static String userData2;
    public static final String data = "data";
    public static String web = "https://www.pizzeriavetri.com/menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userData = getIntent().getStringExtra(MainActivity.data);
        userData2 = getIntent().getStringExtra(About.data);

        //if about
        if(userData2!=null){
            userData = userData2;
        }




        Button menu = (Button)findViewById(R.id.button);
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
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public void onClickMenu(View view) {

        Intent intent = new Intent(this, Menu.class);
        intent.putExtra(data, userData);
        startActivity(intent);
    }

    public void onClickAbout(View view) {

        Intent intent = new Intent(this, About.class);
        intent.putExtra(data,userData);
        startActivity(intent);
    }

    public void onClickCustomer(View view) {

        Intent intent = new Intent(this, CustomerInfo.class);
        intent.putExtra(data,userData);
        startActivity(intent);
    }
}
