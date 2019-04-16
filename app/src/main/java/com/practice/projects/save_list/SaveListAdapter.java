package com.practice.projects.save_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SaveListAdapter extends RecyclerView.Adapter<SaveListAdapter.SaveListViewHolder> {
    @NonNull
    @Override
    public SaveListAdapter.SaveListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SaveListAdapter.SaveListViewHolder saveListViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SaveListViewHolder extends RecyclerView.ViewHolder{

        public SaveListViewHolder(@NonNull View itemView) {
            super(itemView);
            final TextView ListItemView;


        }
    }
}
