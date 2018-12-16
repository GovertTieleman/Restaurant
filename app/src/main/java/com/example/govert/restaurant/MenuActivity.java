package com.example.govert.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemRequest.Callback {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // get ListView
        lv = (ListView) findViewById(R.id.categories);

        // get requested category
        String category = (String) getIntent().getStringExtra("selected_category");

        // request categories
        MenuItemRequest x = new MenuItemRequest(this);
        x.getMenuItems(this, category);

        // set listener
        lv.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {
        // make adapter
        MenuItemAdapter adapter = new MenuItemAdapter(this, 0, menuItems);

        //set adapter
        lv.setAdapter(adapter);
    }

    @Override
    public void gotMenuItemsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // get category
            MenuItem item = (MenuItem) parent.getItemAtPosition(position);

            // make intent
            Intent intent = new Intent(MenuActivity.this, DetailActivity.class);
            intent.putExtra("selected_item", item);

            // start MenuActivity with intent
            startActivity(intent);
        }
    }
}
