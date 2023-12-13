package com.syahrul.stockopnamebarang.Model;

import com.google.gson.annotations.SerializedName;

public class Warehouse {
    private String id_warehouse;
    private String name;

    public Warehouse(String id_warehouse, String warehouse){
        this.id_warehouse = id_warehouse;
        this.name = name;
    }

    @SerializedName("id_warehouse")
    public String getId_warehouse() {return id_warehouse;}
    public void setId_warehouse(String id_warehouse) {
        this.id_warehouse = id_warehouse;
    }

    @SerializedName("name")
    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
}
