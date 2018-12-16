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

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    public void getCategories(Callback activity) {
        // set activity
        this.activity = activity;

        // create RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);

        // create JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        // get JSONArray
        try {
            JSONArray array = response.getJSONArray("categories");

            // initialize ArrayList
            ArrayList<String> categories = new ArrayList<>();

            // iterate over categories, adding them to the ArrayList
            for (int i = 0; i < array.length(); i++) {
                categories.add(array.getString(i));
            }

            // perform Callback to activity
            activity.gotCategories(categories);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
