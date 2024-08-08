package com.example.assignmentchatwise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignmentchatwise.databinding.ItemCardviewBinding;
import com.example.assignmentchatwise.modals.Products;

import java.util.ArrayList;
import java.util.List;

public class ShopProductsAdapter extends RecyclerView.Adapter<ShopProductsAdapter.ViewHolder> {
   public ArrayList<Products> productsList;
   public Context context;
   public onShopItemClick onShopItemClick;
    public ShopProductsAdapter(ArrayList<Products> productsList, Context context, onShopItemClick shopItemClick) {
        this.productsList = productsList;
        this.context = context;
        this.onShopItemClick = shopItemClick;
    }

    @NonNull
    @Override
    public ShopProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,parent,false);
        return new ViewHolder(view,onShopItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopProductsAdapter.ViewHolder holder, int position) {
        holder.binding.titleTv.setText(productsList.get(position).getTitle());
        holder.binding.descriptionTv.setText(productsList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShopItemClick.directToShopDetails(productsList,holder.getAdapterPosition());
            }
        });
        Glide.with(context).load(productsList.get(position).getImages().get(0)).into(holder.binding.imageView);
    }

    public void updateData(List<Products> products){
        productsList.clear();
        productsList.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCardviewBinding binding;
        public ViewHolder(@NonNull View itemView, ShopProductsAdapter.onShopItemClick onShopItemClick) {
            super(itemView);
            binding = ItemCardviewBinding.bind(itemView);
        }
    }
    public interface onShopItemClick{
        void directToShopDetails(ArrayList<Products> products,int position);
    }
}
