package com.syahrul.stockopnamebarang.Model;

import com.google.gson.annotations.SerializedName;

public class Item {
    private String id;
    private ItemName items;
    private String warehouse_id;
    private String category_id;
    private String unit_id;
    private String code;
    private String series;
    private String description;
    private String total;
    private String vendor;
    private String image;

    public Item(
            String id,
            ItemName items,
            String warehouse_id,
            String category_id,
            String unit_id,
            String code,
            String series,
            String description,
            String total,
            String vendor,
            String image
            ){
        this.id = id;
        this.items = items;
        this.warehouse_id = warehouse_id;
        this.category_id = category_id;
        this.unit_id = unit_id;
        this.code = code;
        this.series = series;
        this.description = description;
        this.total = total;
        this.vendor = vendor;
        this.image = image;
    }

    @SerializedName("id")
    public String getId() {return id;}
    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("items")
    public ItemName getItems() {
        return items;
    }

    public void setItems(ItemName items) {
        this.items = items;
    }

    @SerializedName("warehouse_id")
    public String setId_warehouse() {return warehouse_id;}
    public void setId_warehouse(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    @SerializedName("category_id")
    public String getId_category() {return category_id;}
    public void setId_category(String category_id) {
        this.category_id = category_id;
    }

    @SerializedName("unit_id")
    public String getId_unit() {return unit_id;}
    public void setId_unit(String unit_id) {
        this.unit_id = unit_id;
    }

    @SerializedName("code")
    public String get_code() {return code;}
    public void set_code(String code) {
        this.code = code;
    }

    @SerializedName("series")
    public String get_series() {return series;}
    public void set_series(String series) {
        this.series = series;
    }

    @SerializedName("description")
    public String get_description() {return description;}
    public void set_description(String description) {
        this.description = description;
    }

    @SerializedName("total")
    public String get_total() {return total;}
    public void set_total(String total) {
        this.total = total;
    }

    @SerializedName("vendor")
    public String get_vendor() {return vendor;}
    public void set_vendor(String vendor) {
        this.vendor = vendor;
    }

    @SerializedName("image")
    public String get_image() {return image;}
    public void set_image(String image) {
        this.image = image;
    }

    public static class User {
        private String id_user;
        private String email;
        private String password;
        private String role;

        public User(String id_user, String email, String password, String role){
            this.id_user = id_user;
            this.email = email;
            this.password = password;
            this.role = role;
        }

        @SerializedName("id_user")
        public String getId_user() {return id_user;}
        public void setId_user(String id_user) {this.id_user = id_user;}

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
}
