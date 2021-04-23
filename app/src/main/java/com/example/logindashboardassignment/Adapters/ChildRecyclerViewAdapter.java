package com.example.logindashboardassignment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.logindashboardassignment.Models.ChildModel;
import com.example.logindashboardassignment.R;

import java.util.ArrayList;

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.MyViewHolder> {

    public ArrayList<ChildModel> childModelArrayList;
    Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView clientName;
        public TextView clientFeedback;
        public ImageView clientImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            clientName = itemView.findViewById(R.id.client_name);
            clientFeedback = itemView.findViewById(R.id.client_feedback);
            clientImage = itemView.findViewById(R.id.client_image);

        }
    }

    public ChildRecyclerViewAdapter(ArrayList<ChildModel> arrayList, Context mContext) {
        this.cxt = mContext;
        this.childModelArrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_one_recyclerview_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChildModel currentItem = childModelArrayList.get(position);

        holder.clientName.setText(currentItem.getClientName());
        holder.clientFeedback.setText(currentItem.getClientFeedback());
        if(currentItem.getClientName().length()==0){
            holder.clientImage.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return childModelArrayList.size();
    }
}
