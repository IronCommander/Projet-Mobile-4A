package com.github.natha.pizza.model;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("index.php")
    Call<List<Pizza>> get();
}
