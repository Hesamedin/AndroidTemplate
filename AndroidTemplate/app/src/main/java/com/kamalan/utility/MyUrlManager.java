package com.kamalan.utility;

/**
 * Created by Hesam on 15/04/14
 */
public class MyUrlManager {

    private static String HOST_NAME = "http://www.kamalan.com";

    private static String PRODUCTS = "/products";


    public static String getProductsUrl() {
        return HOST_NAME + PRODUCTS;
    }
}
