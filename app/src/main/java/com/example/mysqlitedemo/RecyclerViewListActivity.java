package com.example.mysqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);
        RecyclerView rv_profile;
        rv_profile=findViewById(R.id.rv_profile);


        DataBaseHelper dataBaseHelper=new DataBaseHelper(this);
        List<Profile> listList=new ArrayList<>();
        listList=dataBaseHelper.readData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rv_profile.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView


        System.out.println(listList);
        Adapter adapter = new Adapter(this, listList);
        rv_profile.setAdapter(adapter);




    }
}