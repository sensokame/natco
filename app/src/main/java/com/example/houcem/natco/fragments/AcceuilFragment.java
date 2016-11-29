package com.example.houcem.natco.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.houcem.natco.R;
import com.example.houcem.natco.data.NewsFeedItem;
import com.example.houcem.natco.services.NewsFeedAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;



public class AcceuilFragment extends android.app.Fragment {

    private NewsFeedItem[] news;
    private View mView ;
    private ListView listView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_acceuil, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("News");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Map> value = (Map)dataSnapshot.getValue();

                news = new NewsFeedItem[value.size()];

                int i =0;

                for(String key: value.keySet()){
                 Map<String,String> item = value.get(key);
                    news[i] =new NewsFeedItem(item.get("description"),item.get("image"));

                    i++;
                }

                listView = (ListView) mView.findViewById(R.id.news_list);
                NewsFeedAdapter songAdapter = new NewsFeedAdapter(getActivity(), news);


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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
