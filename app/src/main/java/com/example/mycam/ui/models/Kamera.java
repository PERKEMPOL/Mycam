package com.example.mycam.ui.models;

public class Kamera {
    public int image;
    public String name;
    public String harga;


    public Kamera(int image, String name, String harga) {
        this.image = image;
        this.name = name;
        this.harga = harga;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
