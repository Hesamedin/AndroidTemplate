package com.allstarxi.app;

/**
 * Created by liam on 4/5/14.
 */
public class PlayerData
{
    public String country;
    public String name;
    public String price;
    public String type;

    public PlayerData() {
        super();
    }

    public PlayerData(String country, String name, String price, String type) {
        super();
        this.country = country;
        this.name = name;
        this.price = price;
        this.type = type;
    }
}