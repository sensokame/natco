package com.example.houcem.natco.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.houcem.natco.R;
import com.example.houcem.natco.data.HotelStructure;
import com.example.houcem.natco.data.Session;
import com.example.houcem.natco.data.Sessions;

/**
 * Created by houcem on 06/12/16.
 */

public class agendaAdapter extends BaseAdapter {

    Context context;
    Sessions sessions[] = null;

    public agendaAdapter(Context context, Sessions[] sessions) {
        this.context = context;
        this.sessions = sessions;
    }

    @Override
    public int getCount() {
        return sessions.length;
    }

    @Override
    public Object getItem(int i) {
        return sessions[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        agendaHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sessions_item, viewGroup, false);
            viewHolder = new agendaHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (agendaHolder)view.getTag();
        }

        Sessions currentItem = (Sessions) getItem(i);
        viewHolder.itemTime.setText(currentItem.getTime() + " : ");
        sessionsAdapter sessionsTime = new sessionsAdapter(context,currentItem.getSessions());
        viewHolder.sessionsList.setAdapter(sessionsTime);





        return view;
    }
    private class agendaHolder {

        TextView itemTime;
        ListView sessionsList;

        public agendaHolder(View view) {
            itemTime = (TextView) view.findViewById(R.id.sessions_time);
            sessionsList = (ListView) view.findViewById(R.id.sessions_content);
        }
    }
}
