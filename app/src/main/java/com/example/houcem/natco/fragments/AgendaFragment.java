package com.example.houcem.natco.fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.example.houcem.natco.R;
import com.example.houcem.natco.data.NewsFeedItem;
import com.example.houcem.natco.data.Session;
import com.example.houcem.natco.data.Sessions;
import com.example.houcem.natco.services.NewsFeedAdapter;
import com.example.houcem.natco.services.agendaAdapter;
import com.example.houcem.natco.services.sessionsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class AgendaFragment extends Fragment {

    private Sessions[] agenda;
    private GridView listView;
    private View mView;
    private Button mDay1Button;
    private Button mDay2Button;
    private Button mDay3Button;
    private Button mDay4Button;
    private String mDay;




    public AgendaFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_agenda, container, false);
        SelectDay("day 1");
        mDay1Button = (Button)mView.findViewById(R.id.day1);
        mDay1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectDay("day 1");
            }
        });
        mDay2Button = (Button)mView.findViewById(R.id.day2);
        mDay2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectDay("day 2");
            }
        });
        mDay3Button = (Button)mView.findViewById(R.id.day3);
        mDay3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectDay("day 3");
            }
        });
        mDay4Button = (Button)mView.findViewById(R.id.day4);
        mDay4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectDay("day 4");
            }
        });


        return mView;
    }
    public void SelectDay(String day){
        mDay = day;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("agenda");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Map<String,Map> value = (Map)dataSnapshot.getValue();
                Map<String,Map> values = value.get(mDay);

                agenda = new Sessions[values.size()];
                int i =0;

                SortedSet<String> keys = new TreeSet<String>(values.keySet());
                for(String key: keys){
                    Map<String,String> item = values.get(key);
                    Session[] sessions = new Session[item.size()];
                    int j=0;
                    for(String cle:item.keySet()){
                        sessions[j] = new Session(item.get(cle),cle);

                        j++;
                    }


                    agenda[i] =new Sessions(key,sessions);

                    i++;
                }

                listView = (GridView) mView.findViewById(R.id.agenda_list);
                agendaAdapter songAdapter = new agendaAdapter(getActivity(), agenda);


                listView.setAdapter(songAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
