package com.example.logindashboardassignment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindashboardassignment.Models.ChildModel;
import com.example.logindashboardassignment.Models.ParentModel;
import com.example.logindashboardassignment.R;

import java.util.ArrayList;

public class ParentRecyclerViewAdapter extends RecyclerView.Adapter<ParentRecyclerViewAdapter.MyViewHolder>  {
    private ArrayList<ParentModel> parentModelArrayList;
    public Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public RecyclerView childRecyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.movie_category);
            childRecyclerView = itemView.findViewById(R.id.child_recyclerview);
        }
    }

    public ParentRecyclerViewAdapter(ArrayList<ParentModel> exampleList, Context context) {
        this.parentModelArrayList = exampleList;
        this.cxt = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_recyclerview_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return parentModelArrayList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ParentModel currentItem = parentModelArrayList.get(position);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(cxt, LinearLayoutManager.HORIZONTAL, false);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setHasFixedSize(true);

        holder.category.setText(currentItem.getCategory_title());
        ArrayList<ChildModel> arrayList = new ArrayList<>();


        if (parentModelArrayList.get(position).getCategory_title().equals("Start Investing")) {
            arrayList.add(new ChildModel("","Secure your future."));

        }


        if (parentModelArrayList.get(position).getCategory_title().equals("Goal Calculator")) {
            arrayList.add(new ChildModel("","Forecast your finacial needs"));

        }

        if (parentModelArrayList.get(position).getCategory_title().equals("Why invest with us")) {
            arrayList.add(new ChildModel("Client Name1","Thanks,I've made 5X on my profit"));
            arrayList.add(new ChildModel("Client Name2","Thanks,I've made 5X on my profit"));
            arrayList.add(new ChildModel("Client Name3","Thanks,I've made 5X on my profit"));
        }

        if (parentModelArrayList.get(position).getCategory_title().equals("News")) {
            arrayList.add(new ChildModel("","4 stock to watch in coming week"));
            arrayList.add(new ChildModel("","Top companies quater results"));

        }



        ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(arrayList,holder.childRecyclerView.getContext());
        holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
    }
}
