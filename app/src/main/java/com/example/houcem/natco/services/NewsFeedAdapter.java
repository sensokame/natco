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


/**
 * Created by houcem on 22/11/16.
 */

public class NewsFeedAdapter extends BaseAdapter {
    Context context;
    NewsFeedItem news[] = null;

    public NewsFeedAdapter(Context context,NewsFeedItem[] news) {
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

        NewsHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.news_feed_item, parent, false);
            viewHolder = new NewsHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (NewsHolder) convertView.getTag();
        }

        NewsFeedItem currentItem = (NewsFeedItem) getItem(position);
        new DownloadImageTask(viewHolder.itemImage)
                .execute(currentItem.getImageURL());
        viewHolder.itemDescription.setText(currentItem.getDescription());

        return convertView;


    }



    private class NewsHolder {
        ImageView itemImage;
        TextView itemDescription;

        public NewsHolder(View view) {
            itemImage = (ImageView) view.findViewById(R.id.news_feed_image);
            itemDescription = (TextView) view.findViewById(R.id.news_feed_description);
        }
    }
}

