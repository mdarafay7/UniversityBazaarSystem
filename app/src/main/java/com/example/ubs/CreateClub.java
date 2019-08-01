package com.example.ubs;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateClub extends AppCompatActivity {

    private EditText clubName, clubDescription, clubAdmin, clubMember;
    private FirebaseAuth auth;
    private ArrayList<String> clubMembers;
    private LinearLayout Llayout, dummyLayout;
    private Button btnSave;
    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_club);
        mFirestore=FirebaseFirestore.getInstance();
        /*
            get all values from the screen as well as club admin
         */

         clubName = findViewById(R.id.clubName);
        clubDescription = findViewById(R.id.ClubDesc);
        clubMember = findViewById(R.id.addUser);
        btnSave = findViewById(R.id.btn);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ClubName=clubName.getText().toString();
                String ClubDesc =clubDescription.getText().toString();
                String user=clubMember.getText().toString();
                Map<String,String> clubMap=new HashMap<>();
                clubMap.put("ClubName",ClubName);
                clubMap.put("Desc",ClubDesc);
                clubMap.put("User",user);
                mFirestore.collection("Clubs").add(clubMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CreateClub.this,"Club Added",Toast.LENGTH_LONG).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error=e.getMessage();
                        Toast.makeText(CreateClub.this,"ERROR"+error,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
