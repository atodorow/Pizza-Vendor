package com.example.atod.pizzavendor;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dhaval.mehta on 06-Jul-18.
 */

public class StripeResponse {

    @SerializedName("data")
    public String data;

    @SerializedName("transaction_id")
    public String transaction_id;

    @SerializedName("msg")
    public String msg;
}
