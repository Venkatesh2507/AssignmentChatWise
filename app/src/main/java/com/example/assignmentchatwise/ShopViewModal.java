package com.example.assignmentchatwise;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignmentchatwise.modals.Products;
import com.example.assignmentchatwise.modals.ShopItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopViewModal extends ViewModel {
    private final MutableLiveData<List<Products>> productsMutableLiveData = new MutableLiveData<>();;
    private static final String TAG = "ShopViewModal";
    public LiveData<List<Products>> getProducts(){
        return productsMutableLiveData;
    }

    public void fetchShops(){
        ShopItemApi api = RetrofitClient.getRetrofitClient().create(ShopItemApi.class);
        Call<ShopItem> call = api.getShopProducts();
        call.clone().enqueue(new Callback<ShopItem>() {
            @Override
            public void onResponse(Call<ShopItem> call, Response<ShopItem> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    productsMutableLiveData.setValue(response.body().getProducts());

                }
            }

            @Override
            public void onFailure(Call<ShopItem> call, Throwable throwable) {
                Log.d(TAG, "onFailure: "+throwable.getMessage());
            }
        });
    }


}
