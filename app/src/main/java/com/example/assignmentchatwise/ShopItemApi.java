package com.example.assignmentchatwise;

import com.example.assignmentchatwise.modals.Products;
import com.example.assignmentchatwise.modals.ShopItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ShopItemApi {
    @GET("/products")
    Call<ShopItem> getShopProducts();
}
