package com.example.houcem.natco.services;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.houcem.natco.R;
import com.example.houcem.natco.data.Session;
import com.example.houcem.natco.data.Sessions;

/**
 * Created by houcem on 06/12/16.
 */

public class agendaAdapter extends BaseAdapter {

    Context context;
    Sessions sessions[] = null;
    Session soussessions[] = null;
    int position;
    String description;

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
        if(i == 0){
            viewHolder.itemTime.setText(" ");
            Button session = new Button(context);
            session.setText(R.string.first_item_agenda);
            session.setBackgroundColor(0x00000000);

            session.setOnClickListener(new MyOnClickListener(context.getString(R.string.agenda_description)));
            viewHolder.sessionsList.addView(session);
        }else {
            viewHolder.itemTime.setText(currentItem.getTime() + " : ");
            soussessions = currentItem.getSessions();
            Log.e("seeing length here ._.",String.valueOf(soussessions.length));
            for(int j=0;j<soussessions.length;j++){
                Button session = new Button(context);
                session.setText(soussessions[j].getName());
                description = soussessions[j].getDescription();
                position = j;

                //session.setBackgroundColor(0x00000000);
                session.setBackground(context.getResources().getDrawable(R.drawable.border));
                viewHolder.sessionsList.addView(session);
            }
        }


//        sessionsAdapter sessionsTime = new sessionsAdapter(context,currentItem.getSessions());
//        viewHolder.sessionsList.setAdapter(sessionsTime);






        return view;
    }
    class MyOnClickListener implements View.OnClickListener
    {
        private final String position;
        Toast last = Toast.makeText(context,null,Toast.LENGTH_LONG);

        public MyOnClickListener(String position)
        {
            this.position = position;
        }


        public void onClick(View v)
        {
            // Preform a function based on the position
            last.setText(position);
            last.show();
        }
    }
//    public int getPosition(){
//        return
//    }
    private class agendaHolder {

        TextView itemTime;
//       ListView sessionsList;
LinearLayout sessionsList;

        public agendaHolder(View view) {
            itemTime = (TextView) view.findViewById(R.id.sessions_time);
            sessionsList = (LinearLayout) view.findViewById(R.id.sessions_content);
        }
    }
}
