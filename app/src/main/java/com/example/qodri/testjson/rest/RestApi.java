package com.example.qodri.testjson.rest;

/**
 * Created by irwancannady on 11/13/16.
 */




import com.example.qodri.testjson.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Teknorial on 06/02/2016.
 */
public interface RestApi {

    @GET("contohjson")
    Call<Example> getDataAdmin();
}
