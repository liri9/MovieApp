package com.example.movieapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.init.AppManager;
import com.example.movieapp.models.Group;
import com.example.movieapp.models.Session;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class Adapter_Session_List extends RecyclerView.Adapter<Adapter_Session_List.SessionViewHolder> {

    private AppManager appManager;
    private List<Session> sessions;
    private Adapter_Session_List.OnItemClickListener listener;


    public Adapter_Session_List(List<Session> sessionsList) {
        sessions = sessionsList;
    }

    public void updateList(List<Session> sessionsList) {
        sessions = sessionsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter_Session_List.SessionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.group_list_fragment, viewGroup, false);
        return new Adapter_Session_List.SessionViewHolder(view);
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(Adapter_Session_List.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Session_List.SessionViewHolder holder, int position) {
        Session session = getItem(position);
        holder.groupFrag_LBL_name.setText(session.getGroup().getName());
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
        return sessions == null ? 0 : sessions.size();
    }

    private Session getItem(int position) {
        return sessions.get(position);
    }


    public class SessionViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView groupFrag_LBL_name;
        private MaterialCardView group_list_fragment_crd;


        SessionViewHolder(View itemView) {
            super(itemView);
            groupFrag_LBL_name = itemView.findViewById(R.id.groupFrag_LBL_name);
            group_list_fragment_crd= itemView.findViewById(R.id.group_list_fragment_crd);
        }


    }

}
