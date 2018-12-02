package com.example.govert.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;

    public MenuItemRequest(Context context) {
        this.context = context;
    }

    public void getMenuItems(Callback activity, String category) {
        // set activity
        this.activity = activity;

        // create RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);

        // create JSONObjectRequest
        String url = "https://resto.mprog.nl/menu?category=" + category;
        JsonObjectRequest jsonObjectRequest = new
                JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuItemsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        // get JSONArray
        try {
            JSONArray array = response.getJSONArray("items");

            // initialize ArrayList of menuItems
            ArrayList<MenuItem> menuItems = new ArrayList<>();

            // iterate over items, adding them to the ArrayList
            for (int i = 0; i < array.length(); i++) {
                // get object
                JSONObject object = array.getJSONObject(i);

                // get values
                String name = object.getString("name");
                String description = object.getString("description");
                String imageURL = object.getString("image_url");
                String price = object.getString("price");
                String category = object.getString("category");

                // add to menuItems
                MenuItem item = new MenuItem(name, description, imageURL, price, category);
                menuItems.add(item);
            }

            // perform Callback to activity
            activity.gotMenuItems(menuItems);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menuItems);
        void gotMenuItemsError(String message);
    }
}
