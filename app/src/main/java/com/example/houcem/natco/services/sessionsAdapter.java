package com.example.houcem.natco.services;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.houcem.natco.R;
import com.example.houcem.natco.data.Session;

/**
 * Created by houcem on 08/12/16.
 */

public class sessionsAdapter extends BaseAdapter {
    private Context context;
    private Session sessions[] = null;
    private Session currentItem;

    public sessionsAdapter(Context context, Session[] sessions) {
        this.context = context;
        this.sessions = sessions;
    }

    @Override
    public int getCount() {
        return sessions.length;
    }

    @Override
    public Object getItem(int j) {
        return sessions[j];
    }

    @Override
    public long getItemId(int j) {
        return j;
    }

    @Override
    public View getView(int j, View view, ViewGroup viewGroup) {
        sessionHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.session_item, viewGroup, false);
            viewHolder = new sessionHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (sessionHolder) view.getTag();
        }

        currentItem = (Session) getItem(j);
        viewHolder.sessionName.setText(currentItem.getName());
        Log.e("inside adapter" , currentItem.getName());
        viewHolder.sessionName.setOnClickListener(new View.OnClickListener() {
            Toast last = Toast.makeText(context,null,Toast.LENGTH_LONG);

            @Override
            public void onClick(View view) {
                last.setText(currentItem.getDescription());
                last.show();
            }
        });
        return view;
    }
    private class sessionHolder {

        Button sessionName;

        public sessionHolder(View view) {
            sessionName = (Button) view.findViewById(R.id.session_name);
        }
    }

}
