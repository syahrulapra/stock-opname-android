package com.syahrul.stockopnamebarang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.syahrul.stockopnamebarang.Auth.AuthManager;
import com.syahrul.stockopnamebarang.Model.ServerResponse;
import com.syahrul.stockopnamebarang.Model.User;
import com.syahrul.stockopnamebarang.Rest.ApiClient;
import com.syahrul.stockopnamebarang.Rest.ApiInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

//    EditText etEmail, etPassword;

    TextInputEditText etEmail, etPassword;
    Button btnLogin;

    ApiInterface mApiInterface;
    AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        authManager = new AuthManager(LoginActivity.this);

        etEmail = findViewById(R.id.et_loginEmail);
        etPassword = findViewById(R.id.et_loginPassword);
        btnLogin = findViewById(R.id.bt_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email dan Password harus di isi", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(null, email, password, null);
                Call<ServerResponse> loginCall = mApiInterface.login(user);

                loginCall.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (response != null && response.isSuccessful()) {
                            if (response.body() != null) {
                                authManager.saveAccessToken(response.body().getToken());
                                Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (response != null && response.errorBody() != null) {
                                try {
                                    String errorBody = response.errorBody().string();
                                    Log.e("LoginError", "Error response body: " + errorBody);
                                    Toast.makeText(LoginActivity.this, errorBody, Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Log.e("LoginError", "Login failed", t);
                        Toast.makeText(LoginActivity.this, "Login failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}