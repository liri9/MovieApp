package com.example.movieapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.init.AppManager;
import com.example.movieapp.models.Group;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class Adapter_Categories_List  extends RecyclerView.Adapter<Adapter_Categories_List.CategoriesViewHolder> {

    private AppManager appManager;
    private List<String> categories;
    private Adapter_Categories_List.OnItemClickListener listener;


    public Adapter_Categories_List(List<String> categoryList) {
        categories = categoryList;
    }

    public void updateList(List<String> eventList) {
        categories = eventList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter_Categories_List.CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_list_fragment, viewGroup, false);
        return new Adapter_Categories_List.CategoriesViewHolder(view);
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(Adapter_Categories_List.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Categories_List.CategoriesViewHolder holder, int position) {
        String catregory = getItem(position);
        holder.category_LBL_name.setText(catregory);
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    private String getItem(int position) {
        return categories.get(position);
    }



    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView category_LBL_name;



        CategoriesViewHolder(View itemView) {
            super(itemView);
            category_LBL_name = itemView.findViewById(R.id.category_LBL_name);
        }


    }

}

