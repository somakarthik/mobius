package com.app.mobiustest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerAPI {

    @GET("4c663239-03af-49b5-bcb3-0b0c41565bd2")
    Call<List<Example>> getOfferDetails();
}
