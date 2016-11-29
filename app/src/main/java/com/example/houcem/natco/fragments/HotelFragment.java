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
import com.example.houcem.natco.data.HotelStructure;
import com.example.houcem.natco.services.HotelAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class HotelFragment extends Fragment {



    private HotelStructure[] hotel ;
    private View mView ;
    private ListView listView;

    public HotelFragment() {
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
        mView = inflater.inflate(R.layout.fragment_hotel, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Hotel");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Map> value = (Map)dataSnapshot.getValue();

                hotel = new HotelStructure[value.size()];

                int i =0;
                for(String key: value.keySet()){
                    Map<String,String> item = value.get(key);
                    hotel[i] =new HotelStructure(item.get("name"),item.get("image"),item.get("description"),item.get("directions"));

                    i++;
                }

                listView = (ListView) mView.findViewById(R.id.hotel_list);
                HotelAdapter songAdapter = new HotelAdapter(getActivity(), hotel);

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
