package com.syahrul.stockopnamebarang.Model;

import com.google.gson.annotations.SerializedName;

public class ItemName {

    private String item_id;
    private String name;

    public ItemName(String item_id, String name){
        this.item_id = item_id;
        this.name = name;
    }

    @SerializedName("item_id")
    public String getId_item() {return item_id;}
    public void setId_item(String item_id) {
        this.item_id = ItemName.this.item_id;
    }

    @SerializedName("name")
    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }

}
