package com.example.atod.pizzavendor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class Pizzas extends AppCompatActivity {

    public static String type = "";
    public static String size = "";
    public static String price = "";
    public static String foodType = "";
    public static final String  dataTitle = "dataTitle";
    public ListView lv;
    public RadioButton small;
    public RadioButton medium;
    public RadioButton large;
    public static String userData;
    public static final String userInfo = "userInfo";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas);

        String[] items = getResources().getStringArray(R.array.pizzaList);

        lv = findViewById(R.id.pizzaOptions);
        small = findViewById(R.id.small);
        medium = findViewById(R.id.medium);
        large = findViewById(R.id.large);

        userData = getIntent().getStringExtra(Menu.user);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Intent intent = new Intent(Pizzas.this, )


                type = lv.getItemAtPosition(position).toString().trim();



                Toast.makeText(Pizzas.this,"Great choice!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClickNext(View view) {

        this.currency();
        String data = foodType + "," + type+","+size + "," + price;
        Intent intent = new Intent(this, Checkout.class);
        intent.putExtra(dataTitle, data);
        intent.putExtra(userInfo, userData);
        startActivity(intent);
    }

    public void currency(){

        // Plain pricing

        foodType = "pizza";

        if(type.equalsIgnoreCase("Plain") && size.equalsIgnoreCase("small")){
            price = "6.00";
        }

        else if(type.equalsIgnoreCase("Plain") && size.equalsIgnoreCase("medium")){
            price = "9.00";
        }

        else if(type.equalsIgnoreCase("Plain") && size.equalsIgnoreCase("large")){
            price = "12.00";
        }

        //Pepporoni Pricing

        else if(type.equalsIgnoreCase("Pepperoni") && size.equalsIgnoreCase("small")){
            price = "6.00";
        }

        else if(type.equalsIgnoreCase("Pepperoni") && size.equalsIgnoreCase("medium")){
            price = "9.00";
        }

        else if(type.equalsIgnoreCase("Pepperoni") && size.equalsIgnoreCase("large")){
            price = "12.00";
        }
        //PineApple Pricing

        else if(type.equalsIgnoreCase("PineApple") && size.equalsIgnoreCase("small")){
            price = "6.00";
        }

        else if(type.equalsIgnoreCase("PineApple") && size.equalsIgnoreCase("medium")){
            price = "9.00";
        }

        else if(type.equalsIgnoreCase("PineApple") && size.equalsIgnoreCase("large")){
            price = "12.00";
        }

        //White Pricing

        else if(type.equalsIgnoreCase("White") && size.equalsIgnoreCase("small")){
            price = "6.00";
        }

        else if(type.equalsIgnoreCase("White") && size.equalsIgnoreCase("medium")){
            price = "9.00";
        }

        else if(type.equalsIgnoreCase("White") && size.equalsIgnoreCase("large")){
            price = "12.00";
        }

        //MeatLover Pricing

        else if(type.equalsIgnoreCase("MeatLover") && size.equalsIgnoreCase("small")){
            price = "6.00";
        }

        else if(type.equalsIgnoreCase("MeatLover") && size.equalsIgnoreCase("medium")){
            price = "9.00";
        }

        else if(type.equalsIgnoreCase("MeatLover") && size.equalsIgnoreCase("large")){
            price = "12.00";
        }

        //BBQ Chicken Pricing

        else if(type.equalsIgnoreCase("BBQ Chicken") && size.equalsIgnoreCase("small")){
            price = "6.00";
        }

        else if(type.equalsIgnoreCase("BBQ Chicken") && size.equalsIgnoreCase("medium")){
            price = "9.00";
        }

        else if(type.equalsIgnoreCase("BBQ Chicken") && size.equalsIgnoreCase("large")){
            price = "12.00";
        }

        //Buffalo Chicken Pricing

        else if(type.equalsIgnoreCase("Buffalo Chicken") && size.equalsIgnoreCase("small")){
            price = "6.00";
        }

        else if(type.equalsIgnoreCase("Buffalo Chicken") && size.equalsIgnoreCase("medium")){
            price = "9.00";
        }

        else if(type.equalsIgnoreCase("Buffalo Chicken") && size.equalsIgnoreCase("large")){
            price = "12.00";
        }






    }




    public void onClickSmall(View view) {


        small.setChecked(true);
        medium.setChecked(false);
        large.setChecked(false);
        size = "small";

    }

    public void onClickMedium(View view) {
        medium.setChecked(true);
        small.setChecked(false);
        large.setChecked(false);
        size = "medium";
    }


    public void onClickLarge(View view) {

        large.setChecked(true);
        small.setChecked(false);
        medium.setChecked(false);
        size= "large";
    }
}
