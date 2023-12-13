package com.syahrul.stockopnamebarang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.syahrul.stockopnamebarang.Adapter.ItemAdapter;
import com.syahrul.stockopnamebarang.Auth.AuthManager;
import com.syahrul.stockopnamebarang.Model.Item;
import com.syahrul.stockopnamebarang.Model.Kategori;
import com.syahrul.stockopnamebarang.Model.User;
import com.syahrul.stockopnamebarang.Rest.ApiClient;
import com.syahrul.stockopnamebarang.Rest.ApiInterface;
import com.syahrul.stockopnamebarang.Model.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    AuthManager authManager;
    ApiInterface mApiInterface;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        authManager = new AuthManager(MainActivity.this);
        String accessToken = authManager.getAccessToken();

        Call<ServerResponse> itemCall = mApiInterface.getItem("Bearer " + accessToken);
        itemCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response != null && response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    if (serverResponse != null) {
                        List<Item> itemList = serverResponse.getItem();

                        if (itemList != null && !itemList.isEmpty()) {
                            Log.d("Retrofit Get", "data: " + itemList.size());
                            ItemAdapter itemAdapter = new ItemAdapter(itemList);
                            mRecyclerView.setAdapter(itemAdapter);

                        } else {
                            Log.d("Retrofit Get", "Tidak ada data.");
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Gagal Memuat Subjek", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("Retrofit Get", "Gagal melakukan panggilan API: " + t.getLocalizedMessage());

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Gagal melakukan panggilan API: " + t.getLocalizedMessage());
                builder.show();
            }
        });

        ImageButton btn_profile = findViewById(R.id.btnProfile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, btn_profile);
                popup.getMenuInflater().inflate(R.menu.popup_aksi, popup.getMenu());

                Call<ServerResponse> userCall = mApiInterface.getUser("Bearer " + accessToken);
                userCall.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (response != null && response.isSuccessful()) {
                            ServerResponse serverResponse = response.body();
                            User user = serverResponse.getUser();
                            String name = user.getName();
                            String warehouse = user.getWarehouse().getName();

                            MenuItem nameItem = popup.getMenu().findItem(R.id.name);
                            nameItem.setTitle("Name : " + name);

                            MenuItem warehouseItem = popup.getMenu().findItem(R.id.warehouse);
                            warehouseItem.setTitle("Warehouse : " + warehouse);
                        } else {
                            Toast.makeText(MainActivity.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Logout failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.logout) {
                            Call<ServerResponse> logoutCall = mApiInterface.logout("Bearer " + accessToken);
                            logoutCall.enqueue(new Callback<ServerResponse>() {
                                @Override
                                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                    if (response != null && response.isSuccessful()) {
                                        authManager.clearAccessToken();
                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Logout failed", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ServerResponse> call, Throwable t) {
                                    Toast.makeText(MainActivity.this, "Logout failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });
    }
}