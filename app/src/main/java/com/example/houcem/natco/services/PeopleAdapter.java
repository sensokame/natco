package com.example.houcem.natco.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.houcem.natco.R;
import com.example.houcem.natco.data.NewsFeedItem;
import com.example.houcem.natco.data.PeopleStructure;

/**
 * Created by houcem on 23/11/16.
 */

public class PeopleAdapter extends BaseAdapter {
    Context context;
    PeopleStructure news[] = null;

    public PeopleAdapter(Context context,PeopleStructure[] news) {
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

        peopleHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.people_item, parent, false);
            viewHolder = new peopleHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (peopleHolder) convertView.getTag();
        }

        PeopleStructure currentItem = (PeopleStructure) getItem(position);
        new DownloadImageTask(viewHolder.itemImage)
                .execute(currentItem.getImage());
        viewHolder.itemDescription.setText(currentItem.getDescription());
        viewHolder.itemFacebook.setText(currentItem.getFacebook());
        viewHolder.itememail.setText(currentItem.getEmail());
        viewHolder.itemName.setText(currentItem.getName());

        return convertView;


    }



    private class peopleHolder {
        ImageView itemImage;
        TextView itemDescription;
        TextView itemName;
        TextView itemFacebook;
        TextView itememail;

        public peopleHolder(View view) {
            itemImage = (ImageView) view.findViewById(R.id.people_image);
            itemDescription = (TextView) view.findViewById(R.id.people_description);
            itemName = (TextView) view.findViewById(R.id.people_name);
            itemFacebook = (TextView) view.findViewById(R.id.people_facebook);
            itememail = (TextView) view.findViewById(R.id.people_email);
        }
    }
}
