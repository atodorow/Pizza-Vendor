package com.example.atod.pizzavendor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.stripe.android.view.CardInputWidget;
import com.stripe.android.model.Card;

import org.json.JSONObject;


public class CreditCard extends AppCompatActivity {

    public static Card card;
    public static String ccInfo = "ccInfo";
    public static String cardInformation;
    CardInputWidget c;
    EditText cvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        // Both the Credit Card Widget and the CVC Edit Text are referenced.

        c = findViewById(R.id.card_input_widget);
        cvc = findViewById(R.id.cvc);

        // Card widget information Passed to "card" object.




    }

    public void submit(View view) {

        // Card widget information Passed to "card" object.

        card = c.getCard();


        // CVC edit Text information added to "card" object info.

        cardInformation = (card.getNumber().toString() + "," + card.getExpMonth().toString() + "," + card.getExpYear().toString() + "," + cvc.getText().toString());

        onClickSomething(card.getNumber().toString(), card.getExpMonth().toString(), card.getExpYear().toString(), cvc.getText().toString());




        // CVC edit Text information added to "card" object info.




     Intent intent = new Intent(this, com.example.atod.pizzavendor.Cart.class);
     intent.putExtra(ccInfo, cardInformation);

        startActivity(intent);
    }

    public void onClickSomething(String cardNumber, String cardExpMonth, String cardExpYear, String cardCVC) {
        card = new Card(cardNumber, Integer.parseInt(cardExpMonth), Integer.parseInt(cardExpYear), cardCVC);

    }

    public void validate(){


    }

    public void sendDatatoServer(){

        final JSONObject token = new JSONObject();


    }
}
