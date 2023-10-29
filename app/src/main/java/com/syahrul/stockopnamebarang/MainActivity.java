package com.syahrul.stockopnamebarang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        LinearLayout linearLayout = findViewById(R.id .LinearLayout);
        linearLayout.setOnClickListener(v -> {
            Intent detail = new Intent(MainActivity.this, DetailActivity.class);
            startActivity(detail);
        });

    }
}