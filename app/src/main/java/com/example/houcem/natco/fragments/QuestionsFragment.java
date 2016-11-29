package com.example.houcem.natco.fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.houcem.natco.R;
import com.example.houcem.natco.data.Question;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;


@IgnoreExtraProperties
public class QuestionsFragment extends Fragment implements Serializable {


    /////
    private EditText name;
    private Spinner LC;
    private EditText question;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();


    public QuestionsFragment() {
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
        View mView = inflater.inflate(R.layout.fragment_questions, container, false);


        Button subButton = (Button) mView.findViewById(R.id.submit_question_button);
        name = (EditText)mView.findViewById(R.id.volunteer_questions_name);
        question = (EditText)mView.findViewById(R.id.volunteers_question_content);
        LC = (Spinner)mView.findViewById(R.id.volunteer_questions_LC);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Question volunteer_question = new Question(name.getText().toString(),LC.getSelectedItem().toString(),question.getText().toString());
                myRef.child("questions").push().setValue(volunteer_question);
                name.setText("");
                LC.setSelection(0);
                question.setText("");
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
