package com.example.assignmentchatwise.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.assignmentchatwise.R;
import com.example.assignmentchatwise.RetrofitClient;
import com.example.assignmentchatwise.ShopItemApi;
import com.example.assignmentchatwise.ShopProductsAdapter;
import com.example.assignmentchatwise.ShopViewModal;
import com.example.assignmentchatwise.databinding.ActivityFirstScreenBinding;
import com.example.assignmentchatwise.modals.Products;
import com.example.assignmentchatwise.modals.ShopItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstScreenActivity extends AppCompatActivity implements ShopProductsAdapter.onShopItemClick{
    ActivityFirstScreenBinding binding;
    ArrayList<ShopItem>products;
    ShopProductsAdapter adapter;
    private ShopViewModal shopViewModal;
    private static final String TAG = "FirstScreenActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.progressBar.setVisibility(View.VISIBLE);
        adapter = new ShopProductsAdapter(new ArrayList<>(),FirstScreenActivity.this,this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(FirstScreenActivity.this));
        shopViewModal = new ViewModelProvider(this).get(ShopViewModal.class);
        shopViewModal.getProducts().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products)
            {
                binding.progressBar.setVisibility(View.INVISIBLE);
                adapter.updateData(products);
            }
        });
        shopViewModal.fetchShops();
        binding.recyclerView.setAdapter(adapter);


    }

    @Override
    public void directToShopDetails(ArrayList<Products> products,int position) {
        Intent intent = new Intent(FirstScreenActivity.this,SecondScreenActivity.class);
        intent.putExtra("PRODUCT_TITLE",products.get(position).getTitle());
        intent.putExtra("PRODUCT_DESCRIPTION",products.get(position).getDescription());
        intent.putExtra("PRODUCT_PRICE",products.get(position).getPrice());
        intent.putExtra("PRODUCT_DISC_PRICE",products.get(position).getDiscountPercentage());
        intent.putExtra("PRODUCT_BRAND",products.get(position).getBrand());
        intent.putExtra("PRODUCT_STOCK",products.get(position).getStock());
        intent.putExtra("PRODUCT_AVAILABILITY",products.get(position).getAvailabilityStatus());
        intent.putExtra("PRODUCT_IMAGE",products.get(position).getImages().get(0));

        startActivity(intent);

    }
}