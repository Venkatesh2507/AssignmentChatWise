package com.example.assignmentchatwise.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.assignmentchatwise.R;
import com.example.assignmentchatwise.databinding.ActivitySecondScreenBinding;

public class SecondScreenActivity extends AppCompatActivity {

    ActivitySecondScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String shopTitle = getIntent().getStringExtra("PRODUCT_TITLE");
        String shopDescription = getIntent().getStringExtra("PRODUCT_DESCRIPTION");
        String shopPrice = getIntent().getStringExtra("PRODUCT_PRICE");
        String shopDiscPrice = getIntent().getStringExtra("PRODUCT_DISC_PRICE");
        String shopBrand = getIntent().getStringExtra("PRODUCT_BRAND");
        String shopStock = getIntent().getStringExtra("PRODUCT_STOCK");
        String shopAvailability = getIntent().getStringExtra("PRODUCT_AVAILABILITY");
        String shopImage = getIntent().getStringExtra("PRODUCT_IMAGE");
        binding.title.setText(shopTitle);
        binding.description.setText(shopDescription);
        binding.price.setText(shopPrice);
        binding.discPrice.setText(shopDiscPrice);
        binding.category.setText(shopBrand);
        binding.stock.setText(shopStock);
        binding.availabilityStatus.setText(shopAvailability);
        Glide.with(this).load(shopImage).into(binding.productIv);

    }
}