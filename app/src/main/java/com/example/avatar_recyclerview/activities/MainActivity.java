package com.example.avatar_recyclerview.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avatar_recyclerview.R;
import com.example.avatar_recyclerview.adapters.MyAdapter;
import com.example.avatar_recyclerview.classes.myData;
import com.example.avatar_recyclerview.models.Data;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Data> arr;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter MyAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.myRV);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arr = new ArrayList<Data>();

        for (int i =0 ; i < myData.nameArray.length ; i++ ){
            arr.add ( new Data(
                    myData.nameArray[i],
                    myData.descriptionArray[i],
                    myData.drawableArray[i],
                    myData.id_[i]
            ) );
        }

        MyAdapter = new MyAdapter(arr);
        recyclerView.setAdapter(MyAdapter);


        androidx.appcompat.widget.SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MyAdapter.filter(newText);
                return true;
            }
        });
    }
}