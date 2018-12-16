package com.example.govert.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
    private List<MenuItem> menuItems;

    public MenuItemAdapter(Context context, int resource, List<MenuItem> items) {
        super(context, resource, items);
        menuItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent,
                    false);
        }

        // get currentItem
        MenuItem currentItem = menuItems.get(position);

        // set image for item
        ImageView image =(ImageView) listItem.findViewById(R.id.imageView_item);
        Picasso.get().load(currentItem.getImageURL()).into(image);

        // set name for item
        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentItem.getName());

        // set price for item
        TextView price = (TextView) listItem.findViewById(R.id.textView_price);
        String priceString = "€ " + currentItem.getPrice() + "0";
        price.setText(priceString);

        return listItem;
    }
}
