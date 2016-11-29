package com.example.houcem.natco.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.houcem.natco.R;
import com.example.houcem.natco.data.HotelStructure;

/**
 * Created by houcem on 24/11/16.
 */

public class HotelAdapter extends BaseAdapter {
    Context context;
    HotelStructure news[] = null;

    public HotelAdapter(Context context,HotelStructure[] news) {
        this.context = context;
        this.news = news;
    }


    @Override
    public int getCount() {
        return news.length;
    }

    @Override
    public Object getItem(int i) {
        return news[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        hotelHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hotel_item, parent, false);
            viewHolder = new hotelHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (hotelHolder) convertView.getTag();
        }

        HotelStructure currentItem = (HotelStructure) getItem(position);
        new DownloadImageTask(viewHolder.itemImage)
                .execute(currentItem.getImage());
        viewHolder.itemDescription.setText(currentItem.getDescription());
        viewHolder.itemDirections.setText(currentItem.getDirections());
        viewHolder.itemName.setText(currentItem.getName());

        return convertView;


    }



    private class hotelHolder {
        ImageView itemImage;
        TextView itemDescription;
        TextView itemName;
        TextView itemDirections;

        public hotelHolder(View view) {
            itemImage = (ImageView) view.findViewById(R.id.hotel_image);
            itemDescription = (TextView) view.findViewById(R.id.hotel_description);
            itemName = (TextView) view.findViewById(R.id.hotel_name);
            itemDirections = (TextView) view.findViewById(R.id.hotel_directions);
        }
    }
}
