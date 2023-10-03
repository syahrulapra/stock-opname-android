package com.syahrul.stockopnamebarang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button btnPesan = findViewById(R.id.btnPesan);
        btnPesan.setOnClickListener(v -> {
            Toast.makeText(this, "pesanan dikirim", Toast.LENGTH_SHORT).show();
            Intent pesan = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(pesan);
        });
    }
}