package com.example.atod.pizzavendor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.maps.GoogleMap;


public class About extends AppCompatActivity {

    public static String web = "https://www.pizzeriavetri.com/menu";
    public static String facebook = "https://www.facebook.com/PizzeriaVetri/";
    public static String userData;
    public static final String data = "data";
    public static GoogleMap mMap;
    public static final int PERMISSION_REQUEST_CODE = 1;
    public static final int ERROR_DIALOG_REQUEST=9001;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        Toolbar tool = findViewById(R.id.tool);


        userData = getIntent().getStringExtra(Home.data);

        setActionBar(tool);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_home:
                Intent intent = new Intent(this, Home.class);
                intent.putExtra(data, userData);
                startActivity(intent);

                return true;

            case R.id.action_web:
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(web));

                if (webIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(webIntent);
                }

                return true;

            case R.id.action_logOut:

                System.exit(0);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClickEmail(View view) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "a.tod@comcast.net", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Information");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));


    }

    public boolean onClickFacebook(View view) {

        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebook));

        if (webIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(webIntent);
        }

        return true;


    }

    public void onClickAddress(View view) {

        Toast.makeText(this, "Address: 1615 Chancellor Street, Philadelphia", Toast.LENGTH_LONG).show();
//        if (!(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
//            mMap.setMyLocationEnabled(true);
//        }

        Intent address = new Intent(Intent.ACTION_VIEW);
        address.setData(Uri.parse("geo: 39.9608893, -75.17124860000001?q= 1615 Chancellor Street, Philadelphia"));
        startActivity(address);

    }
}
