package com.example.govert.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get currentItem
        MenuItem currentItem = (MenuItem) getIntent().getSerializableExtra("selected_item");

        // set name for item
        TextView name = (TextView) findViewById(R.id.textView_name);
        name.setText(currentItem.getName());

        // set image for item
        ImageView image =(ImageView) findViewById(R.id.imageView_item);
        Picasso.get().load(currentItem.getImageURL()).into(image);

        // set description for item
        TextView description = (TextView) findViewById(R.id.textView_description);
        description.setText(currentItem.getDescription());

        // set price for item
        TextView price = (TextView) findViewById(R.id.textView_price);
        String priceString = "Price: € " + currentItem.getPrice();
        price.setText(priceString);
    }
}
