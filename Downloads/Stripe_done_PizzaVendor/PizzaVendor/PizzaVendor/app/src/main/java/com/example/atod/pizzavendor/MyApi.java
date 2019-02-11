package com.example.atod.pizzavendor;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dhaval.mehta on 07-07-2018.
 */

public interface MyApi {

    // http://programmingly.com/bvn/test/request.php?
    // cost=1200
    // &tokens=ddnqechwuoiefhwuigwuifgwi
    // &key=5a14ec5b310164f2dfe49e86b06124a1

    @GET("test/request.php?")
    Call<StripeResponse> StripePaymentDone(
            @Query("cost") String cost,
            @Query("tokens") String tokens,
            @Query("key") String key
    );
}
