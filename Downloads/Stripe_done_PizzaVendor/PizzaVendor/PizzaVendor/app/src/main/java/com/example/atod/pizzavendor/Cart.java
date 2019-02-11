package com.example.atod.pizzavendor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;
import com.stripe.android.model.Card;

import org.json.JSONObject;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static com.example.atod.pizzavendor.Checkout.Order;
import com.stripe.android.view.CardInputWidget;


import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Cart extends AppCompatActivity {

    public static String web = "https://www.pizzeriavetri.com/menu";

    public static ArrayList<String> array = new ArrayList<String>();
    public static String[] Items;
    public static String data;
    public static String type;
    public static String bevData;
    public static String total;
    public static String size;
    public static String price;
    public static String name;
    public static String address;
    public static String credit;
    public static String phone;
    public static String totalOrder;
    public static String foodType;
    public static int counter;
    public static String email = "a.tod@comcast.net";

    Card card;

    public static String cardInformation;
    //
    //
    Stripe stripe;
    private static final String PUBLISHABLE_KEY = "pk_test_tu1GqjID12g8zCkNwKjBlKkz";
    float amount = 120f;
    //price for testing
    ProgressDialog pdialog;
//    PIzaVendor Key
//    pk_test_tu1GqjID12g8zCkNwKjBlKkz
//    sk_test_RxyEiwtsVlQNBBi8Uy9Zgvoj


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        // Stripe
        stripe = new Stripe(Cart.this, PUBLISHABLE_KEY);
        pdialog = new ProgressDialog(Cart.this);

        // Intent that receives total order information from previous activity.
        data = getIntent().getStringExtra(Order);

        // Intent that receives String information from beverage activity
        bevData= getIntent().getStringExtra(Beverages.dataTitle);

        // Intent that receives card information from Credit Card activity

        cardInformation = getIntent().getStringExtra(CreditCard.ccInfo);



        TextView text = findViewById(R.id.textView2);

       // text.setText(data);

        // Sets action bar accordingly.

        Toolbar tool = findViewById(R.id.tool);

        setActionBar(tool);

        // Sets Views accordingly

        EditText orderDetails = findViewById(R.id.order);
        TextView cost = findViewById(R.id.textView2);
        Button place = findViewById(R.id.placeOrder);



        // Sets the total price and order details.
        this.setData();
        this.totalPrice();

        cost.setText(total);
        total = null;
        orderDetails.setText(totalOrder);

        if (cardInformation != null) {

            this.split(cardInformation);
        }

        //
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPaymentStripeDialog();
                    }
                }, 120);
            }
        });

        //Dialog for entering the credit card information and shows the processesd price for $120.00
    }


    /*
    * My Stripe Code
    * */
    public void hideSoftKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        //Dialog for entering the details for the price
    }


    public void showPaymentStripeDialog() {
        try {
            final Dialog dialog = new Dialog(Cart.this);
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // for dialog shadow -- Clears the value once it is input
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before -- Title in the dialog box
            dialog.setContentView(R.layout.stripe_payment_popup);

            Button btnDone = (Button) dialog.findViewById(R.id.btnDone);

            // This button is in the stripe_payment_popup layout if you need tofind later.

            final CardInputWidget card_input_widget = (CardInputWidget) dialog.findViewById(R.id.card_input_widget);

            dialog.show();

            /*
            * On this done button click you will get Card info i.e. Account number, Exp Mont and cvv
            *
            * */

            btnDone.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    hideSoftKeyboard();
                    dialog.dismiss();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            pdialog.setMessage("Please wait while your payment process...");
                            pdialog.setCancelable(false);
                            pdialog.show();
                          //  Toast.makeText(getApplicationContext(), "Please wait..", Toast.LENGTH_SHORT).show();

                            Card card = card_input_widget.getCard();
                            /*
                            * If you want to store info as above mentioned
                            * you'll get that info in below function
                            * */
//                            card.getNumber();
//                            card.getExpMonth();
//                            card.getExpYear();
//                            card.getCvcCheck();

                            stripe.createToken(card, PUBLISHABLE_KEY, new TokenCallback() {
                                public void onSuccess(Token token) {
                                    pdialog.dismiss();
                                    // TODO: Send Token information to your backend to initiate a charge
                                    // Toast.makeText(getApplicationContext(), "Token created: " + token.getId(), Toast.LENGTH_LONG).show();

                                    // String tok = String.valueOf(token);
                                    Log.e("==PaymentFragement==", "==TokenId From STRIPE==" + token.getId());

                                    if (token.getId() != null) {
                                        asyncPaymentDoneAPI(token.getId());
                                    }
                                }

                                public void onError(Exception error) {
                                    pdialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Stripe Error: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                    Log.d("Stripe", error.getLocalizedMessage());
                                }
                            });
                        }
                    }, 500);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // http://programmingly.com/bvn/test/request.php?
    // cost=1200
    // &tokens=
    // &key=5a14ec5b310164f2dfe49e86b06124a1


    private void asyncPaymentDoneAPI(String token) {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(Cart.this);
        progressDialog.setMessage("Please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

//        String API = Constants.STRIPE_PAYMENT;
        Retrofit retrofit;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://programmingly.com/bvn/")
                // Here is where I put my server url.
                // When you set up your own server use this website ...https://www.hostgator.com/promo/snappy60?utm_source=bing&utm_medium=brandsearch&kclickid=02874c66-2d02-4067-b482-c8a02456bbd0&utm_source=bing&utm_medium=brandsearch&kclickid=02874c66-2d02-4067-b482-c8a02456bbd0&kenshoo_ida=Host%20Gator%20IDA&msclkid=b012cb81042217ba3d0690c61bc8f78b

                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        int total_amout = (int) (amount * (100));
        MyApi myApi = retrofit.create(MyApi.class);
        Call callApiMethod = null;
        callApiMethod = myApi.StripePaymentDone(
                ""+total_amout,
                token,
                "5a14ec5b310164f2dfe49e86b06124a1"
        );
        Log.e("==Cart==", "==asyncPaymentDoneAPI==OP==http://programmingly.com/bvn/test/request.php?"
                + "\n"
                + "cost=" + total_amout
                + "&tokens=" + token
                + "&key=5a14ec5b310164f2dfe49e86b06124a1"
        );


        callApiMethod.enqueue(new Callback<StripeResponse>() {

            @Override
            public void onResponse(Call<StripeResponse> call, Response<StripeResponse> response) {
                progressDialog.dismiss();
                StripeResponse model = response.body();

                if (model == null) {
                    Log.e("==Cart==", "--asyncPaymentDoneAPI--null response--" + "==Something Wrong=");
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        try {
                            Log.e("==Cart==", "--asyncPaymentDoneAPI---error-" + " -/- " + responseBody.string());
//                            App.showSnackBar(edtEmail, getString(R.string.strSomethingWentwrong));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    //200 sucess
                    Log.e("==Cart==", "===Response== " + response.body().toString());
                    Log.e("==Cart==", "==**==Success==**==asyncPaymentDoneAPI==> " + new Gson().toJson(response.body()));

                    if (model.data != null && model.data.length() > 0) {
                        if (model.data.equalsIgnoreCase("success")) {

                            Log.e("==Transaction_id==","==Your Payment Transaction_id==" + model.transaction_id);

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Cart.this);
                            alertDialogBuilder.setMessage("Transaction successfully processed");
                            alertDialogBuilder.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
//                                            Intent intent = new Intent(Cart.this, Home.class);
//                                            startActivity(intent);

                                            Toast.makeText(Cart.this, "Your order has been placed!", Toast.LENGTH_LONG).show();



                                        }
                                    });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        } else {
                            Log.e("==Cart==", "====Payment Failure====");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<StripeResponse> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    /*
    * Till Here
    * */







    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id){
            case R.id.action_home:
                Intent intent = new Intent(this, Home.class);
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

    public void totalPrice(){

       String [] t = total.split(",");

       double totalCost = 0;

       for(int i = 0; i < t.length; i++){

           totalCost += Double.parseDouble(t[i]);
       }

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

       String tc = formatter.format(totalCost);

       total = "Total price: " + tc;


    }

    public void setData() {

        String[] stuff;

        if(bevData!=null){
            stuff = bevData.split(",");
        }

        else{

            stuff = data.split(",");

        }





//        int i = 0;
//
//
//        while (i < stuff.length) {
//            array.add(stuff[i]);
//            i++;
//        }
//
//            Items =array.toArray(new String[array.size()]);

//        name = array.get(0);
//        address = array.get(1);
//        phone = array.get(2);
//        credit = array.get(3);

        name = stuff[0];
        address = stuff[1];
        phone = stuff[2];
        credit = stuff[3];



        totalOrder = "Customer Name: " + name + "\n" + "Customer Address: " + address + "\n" + "Customer Phone Number: " + phone + "\n";


        int b = 4;
        counter = 4;

        while (b< stuff.length) {


            if(stuff[b].equalsIgnoreCase("pizza")){

                foodType = stuff[b];
                type = stuff[b+1];
                size = stuff[b+2];
                price = stuff[b+3];

                if(total == null){
                    total = price;
                }

                else{
                    total+= ","+ price;
                }

            }

            else if(stuff[b].equalsIgnoreCase("sandwhich")){

            }

            else if(stuff[b].equalsIgnoreCase("beverage")){

                foodType = stuff[b];
                type = stuff[b+1];
                size = stuff[b+2];
                price = stuff[b+3];

                if(total == null){
                    total+=price;
                }

                else{
                    total+= ","+ price;
                }

            }

            else if(stuff[b].equalsIgnoreCase("dessert")){

            }

            else if(stuff[b].equalsIgnoreCase("side")){

            }
            totalOrder += "Item: " + type + "/" + size + "/" + price + "\n";

            b+=counter;
        }

    }

    public void split(String s){

        // Method used to split a string and assign values to Card object.

        String[] stuff;


            stuff = s.split(",");

        card = new Card(stuff[0], Integer.parseInt(stuff[1]), Integer.parseInt(stuff[2]), stuff[3]);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onClickPlace(View view) {

        String[] addresses = {email};

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_EMAIL,"a.tod@comcast.net");
//        //is a string of arrays is used, that can be subsituted to send to multiple emails.
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Information");
//        intent.putExtra(Intent.EXTRA_TEXT, totalOrder);

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","a.tod@comcast.net", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Information");
        emailIntent.putExtra(Intent.EXTRA_TEXT, totalOrder);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));



//        if(intent.resolveActivity(getPackageManager())!=null){
//            startActivity(intent);
//        }


    }

    public void onClickAdd(View view) {

        if(bevData!=null){

            Intent intent = new Intent(this, com.example.atod.pizzavendor.Menu.class);
           // String order = name +","+address+","+phone+","+credit + "," + data;
            intent.putExtra(Order,data);

            startActivity(intent);

        }

        else{

            Intent intent = new Intent(this, com.example.atod.pizzavendor.Menu.class);
            String order = data;
            intent.putExtra(Order,order);

            startActivity(intent);

        }


    }

    public void ccInfo(View view) {

        Intent intent = new Intent(this, com.example.atod.pizzavendor.CreditCard.class);

        startActivity(intent);


    }
}
