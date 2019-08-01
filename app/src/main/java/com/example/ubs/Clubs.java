package com.example.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Clubs extends AppCompatActivity {
private RecyclerView MainList;
private FirebaseFirestore mFirestore;
private ClubsListAdapter clubsListAdapter;
private List<ClubData> ClubsList;
private Button CreateClub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs);
        ClubsList=new ArrayList<>();
        clubsListAdapter=new ClubsListAdapter(ClubsList);
        MainList=(RecyclerView)findViewById(R.id.main_list);
        MainList.setHasFixedSize(true);
        MainList.setLayoutManager(new LinearLayoutManager(this));
        MainList.setAdapter(clubsListAdapter);
        final String TAG="FireLog";
        mFirestore=FirebaseFirestore.getInstance();
        mFirestore.collection("Clubs").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot documentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(e !=null)
                {
                    Log.d(TAG,"Error: "+e.getMessage());
                    return;
                }
                CreateClub=findViewById(R.id.create_club);

                CreateClub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Clubs.this, CreateClub.class);
                        startActivity(intent);
                    }
                });


                for(DocumentChange doc : documentSnapshots.getDocumentChanges())
                {
                    if(doc.getType()==DocumentChange.Type.ADDED)
                    {
                      ClubData clubs=doc.getDocument().toObject(ClubData.class);
                      ClubsList.add(clubs);
                      clubsListAdapter.notifyDataSetChanged();


                    }
                }
            }
        });

        }
    }

