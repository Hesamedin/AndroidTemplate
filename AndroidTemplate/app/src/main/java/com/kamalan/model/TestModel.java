package com.kamalan.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hesam on 15/04/14
 */
public class TestModel {

    public static String TAG_ID = "id";
    public static String TAG_NAME = "name";
    public static String TAG_DESCRIPTION = "description";
    public static String TAG_PRICE = "price";

    private int productId;
    private String name;
    private String description;
    private String price;

    public static List<TestModel> parseJson(String content) {
        List<TestModel> testModelList = new ArrayList<TestModel>();

        if(TextUtils.isEmpty(content) || content.length()<=10)
            return null;

        try {
            JSONArray jsonArray = new JSONArray(content);
            for(int i=0; i<jsonArray.length(); i++) {
//                Log.d(TAG, "Loop => " + i);
                TestModel testModel = new TestModel();
                JSONObject jObject = jsonArray.getJSONObject(i);

                testModel.setProductId(jObject.getInt(TAG_ID));
                testModel.setName(jObject.getString(TAG_NAME));
                testModel.setDescription(jObject.getString(TAG_DESCRIPTION));
                testModel.setPrice(jObject.getString(TAG_PRICE));

                testModelList.add(testModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return testModelList;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
