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

public class Beverages extends AppCompatActivity {

    public static String type = "";
    public static String size = "";
    public static String price = "";
    public static String foodType = "";
    public static String order;
    public static final String dataTitle = "dataTitle";
    public ListView lv;
    public RadioButton small;
    public RadioButton medium;
    public RadioButton large;
    public static String userData;
    public static final String data = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages);


        String[] bevs = getResources().getStringArray(R.array.bevList);

        order = getIntent().getStringExtra(Menu.dataTitle);
       // userData =

        lv = findViewById(R.id.bevOptions);
        small = findViewById(R.id.small);
        medium = findViewById(R.id.medium);
        large = findViewById(R.id.large);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, bevs);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Intent intent = new Intent(Pizzas.this, )


                type = lv.getItemAtPosition(position).toString().trim();


                Toast.makeText(Beverages.this, "Great choice!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClickNext(View view) {

        if(size.equalsIgnoreCase("")||size.equalsIgnoreCase(null)){
            Toast.makeText(Beverages.this, "Please select a size first!", Toast.LENGTH_LONG).show();
            return;
        }

        if(type.equalsIgnoreCase("")||type.equalsIgnoreCase(null)){
            Toast.makeText(Beverages.this, "Please select a beverage first!", Toast.LENGTH_LONG).show();
            return;
        }

        else {


            this.currency();
            String data = foodType + "," + type + "," + size + "," + price;

            order += "," + foodType + "," + type + "," + size + "," + price;

            if (order != null) {


                Intent intent = new Intent(this, Cart.class);
                intent.putExtra(dataTitle, order);
                startActivity(intent);
            } else {

                Intent intent = new Intent(this, Checkout.class);
                intent.putExtra(dataTitle, data);
                startActivity(intent);

            }

        }
    }

    public void currency() {

        // Plain pricing

        foodType = "beverage";

        if (type.equalsIgnoreCase("Coke") && size.equalsIgnoreCase("small")) {
            price = "1.00";
        } else if (type.equalsIgnoreCase("Coke") && size.equalsIgnoreCase("medium")) {
            price = "1.50";
        } else if (type.equalsIgnoreCase("Coke") && size.equalsIgnoreCase("large")) {
            price = "2.00";
        }

        //Pepporoni Pricing

        else if (type.equalsIgnoreCase("Diet Coke") && size.equalsIgnoreCase("small")) {
            price = "1.00";
        } else if (type.equalsIgnoreCase("Diet Coke") && size.equalsIgnoreCase("medium")) {
            price = "1.50";
        } else if (type.equalsIgnoreCase("Diet Coke") && size.equalsIgnoreCase("large")) {
            price = "2.00";
        }
        //PineApple Pricing

        else if (type.equalsIgnoreCase("Dr. Pepper") && size.equalsIgnoreCase("small")) {
            price = "1.00";
        } else if (type.equalsIgnoreCase("Dr. Pepper") && size.equalsIgnoreCase("medium")) {
            price = "1.50";
        } else if (type.equalsIgnoreCase("Dr. Pepper") && size.equalsIgnoreCase("large")) {
            price = "2.00";
        }

        //White Pricing

        else if (type.equalsIgnoreCase("Sprite") && size.equalsIgnoreCase("small")) {
            price = "1.00";
        } else if (type.equalsIgnoreCase("Sprite") && size.equalsIgnoreCase("medium")) {
            price = "1.50";
        } else if (type.equalsIgnoreCase("Sprite") && size.equalsIgnoreCase("large")) {
            price = "2.00";
        }

        //MeatLover Pricing

        else if (type.equalsIgnoreCase("Root Beer") && size.equalsIgnoreCase("small")) {
            price = "1.00";
        } else if (type.equalsIgnoreCase("Root Beer") && size.equalsIgnoreCase("medium")) {
            price = "1.50";
        } else if (type.equalsIgnoreCase("Root Beer") && size.equalsIgnoreCase("large")) {
            price = "2.00";
        }

        //BBQ Chicken Pricing

        else if (type.equalsIgnoreCase("Ice Tea") && size.equalsIgnoreCase("small")) {
            price = "1.00";
        } else if (type.equalsIgnoreCase("Ice Tea") && size.equalsIgnoreCase("medium")) {
            price = "1.50";
        } else if (type.equalsIgnoreCase("Ice Tea") && size.equalsIgnoreCase("large")) {
            price = "2.00";
        }

        //Buffalo Chicken Pricing

        else if (type.equalsIgnoreCase("Lemonade") && size.equalsIgnoreCase("small")) {
            price = "1.00";
        } else if (type.equalsIgnoreCase("Lemonade") && size.equalsIgnoreCase("medium")) {
            price = "1.50";
        } else if (type.equalsIgnoreCase("Lemonade") && size.equalsIgnoreCase("large")) {
            price = "2.00";
        }

        else if (type.equalsIgnoreCase("Orange Soda") && size.equalsIgnoreCase("small")) {
            price = "1.00";
        } else if (type.equalsIgnoreCase("Orange Soda") && size.equalsIgnoreCase("medium")) {
            price = "1.50";
        } else if (type.equalsIgnoreCase("Orange Soda") && size.equalsIgnoreCase("large")) {
            price = "2.00";
        }


    }

    public void onClickLarge(View view) {

        large.setChecked(true);
        small.setChecked(false);
        medium.setChecked(false);
        size= "large";
    }

    public void onClickMedium(View view) {

        medium.setChecked(true);
        small.setChecked(false);
        large.setChecked(false);
        size = "medium";
    }

    public void onClickSmall(View view) {


        small.setChecked(true);
        medium.setChecked(false);
        large.setChecked(false);
        size = "small";

    }
}
