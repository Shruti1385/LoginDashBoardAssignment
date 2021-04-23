package com.example.logindashboardassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.logindashboardassignment.Adapters.ParentRecyclerViewAdapter;
import com.example.logindashboardassignment.Models.ParentModel;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {

    private RecyclerView parentRecyclerView;
    private RecyclerView.Adapter ParentAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager;
    private String name;
    private TextView name_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dash_board);

        name_txt = (TextView) findViewById(R.id.personal_details_txt);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("FullName");
        }

        if(name.length()!=0){
            name_txt.setText(name);
        }

        //set the Categories for each array list set in the `ParentViewHolder`
        //Here its done statically ,can be done dynamically
        parentModelArrayList.add(new ParentModel("Start Investing"));
        parentModelArrayList.add(new ParentModel("Goal Calculator"));
        parentModelArrayList.add(new ParentModel("Why invest with us"));
        parentModelArrayList.add(new ParentModel("News"));

        parentRecyclerView = findViewById(R.id.Parent_recyclerView);
        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(this);
        ParentAdapter = new ParentRecyclerViewAdapter(parentModelArrayList, DashBoardActivity.this);
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);
        ParentAdapter.notifyDataSetChanged();
    }
}