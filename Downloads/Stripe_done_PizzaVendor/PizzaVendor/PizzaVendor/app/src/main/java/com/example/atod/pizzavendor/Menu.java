package com.example.atod.pizzavendor;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    public static String data;
    public static final String dataTitle = "dataTitle";
    public static String userData;
    public static final String user = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        ImageView pizza = (ImageView) findViewById(R.id.imagePizza2);
        ImageView bev = (ImageView) findViewById(R.id.imageBeverages);
        TextView text = findViewById(R.id.textDessert);

        data = getIntent().getStringExtra(Checkout.Order);
        userData = getIntent().getStringExtra(Home.data);

       // text.setText(data);
//        ImageView pasta = (ImageView) findViewById(R.id.imagePasta);
//        ImageView bev = (ImageView) findViewById(R.id.imageBeverages);
//        ImageView sides = (ImageView) findViewById(R.id.imageSides);
//        ImageView dessert = (ImageView) findViewById(R.id.imageDessert);



    }


    public void onClickPizza(View view) {

        Intent intent = new Intent(this, Pizzas.class);
        intent.putExtra(user, userData);
        startActivity(intent);
    }



    public void onClickBev(View view) {

        if(data !=null){

            Intent intent = new Intent(this, Beverages.class);
            intent.putExtra(dataTitle, data);
            intent.putExtra(user, userData);
            startActivity(intent);

        }

        else{

            Intent intent = new Intent(this, Beverages.class);
            startActivity(intent);

        }


    }
}
