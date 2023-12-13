package com.syahrul.stockopnamebarang.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    private String id_user;
    private  Warehouse warehouse;
    private  String name;
    private String email;
    private String password;
    private String role;

    public User(String id_user, Warehouse warehouse, String name, String email, String password, String role){
        this.id_user = id_user;
        this.warehouse = warehouse;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @SerializedName("id_user")
    public String getId_user() {return id_user;}
    public void setId_user(String id_user) {this.id_user = id_user;}
    @SerializedName("warehouse")
    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
    @SerializedName("name")
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @SerializedName("email")
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @SerializedName("password")
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    @SerializedName("role")
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
}
