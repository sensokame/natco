package com.example.houcem.natco.fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.houcem.natco.R;
import com.example.houcem.natco.data.PeopleStructure;
import com.example.houcem.natco.services.PeopleAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static android.content.ContentValues.TAG;


public class ChairFragment extends Fragment {



    private PeopleStructure[] Chair;
    private View mView ;
    private ListView listView;

    public ChairFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_chair, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Chair");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Map> value = (Map)dataSnapshot.getValue();

                Chair = new PeopleStructure[value.size()];

                int i =0;
                for(String key: value.keySet()){
                    Map<String,String> item = value.get(key);
                    Chair[i] =new PeopleStructure(item.get("name"),item.get("image"),item.get("description"),item.get("email"),item.get("facebook"));

                    i++;
                }

                listView = (ListView) mView.findViewById(R.id.chair_list);
                PeopleAdapter songAdapter = new PeopleAdapter(getActivity(), Chair);

                listView.setAdapter(songAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return mView;
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
