package com.example.movieapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.init.AppManager;
import com.example.movieapp.models.Group;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class Adapter_Group_List extends RecyclerView.Adapter<Adapter_Group_List.GroupViewHolder> {

    private AppManager appManager;
    private List<Group> groups;
    private OnItemClickListener listener;


    public Adapter_Group_List(List<Group> groupList) {
        groups = groupList;
    }

    public void updateList(List<Group> eventList) {
        groups = eventList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.group_list_fragment, viewGroup, false);
        return new GroupViewHolder(view);
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        Group group = getItem(position);
        holder.groupFrag_LBL_name.setText(group.getName());
        holder.group_list_fragment_crd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.getAdapterPosition());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return groups == null ? 0 : groups.size();
    }

    private Group getItem(int position) {
        return groups.get(position);
    }


    public class GroupViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView groupFrag_LBL_name;
        private MaterialCardView group_list_fragment_crd;


        GroupViewHolder(View itemView) {
            super(itemView);
            groupFrag_LBL_name = itemView.findViewById(R.id.groupFrag_LBL_name);
            group_list_fragment_crd= itemView.findViewById(R.id.group_list_fragment_crd);

        }


    }

}

