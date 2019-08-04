package com.example.ubs;

import android.os.Bundle;
import android.util.Log;

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

public class UsrMsg extends AppCompatActivity {
    private RecyclerView MainList;
    private FirebaseFirestore mFirestore;
    private UsrMsgListAdapter usrMsgListAdapter;
    private List<UsrMsgData> MsgList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usrmsg);
        MsgList=new ArrayList<>();
        usrMsgListAdapter=new UsrMsgListAdapter(getApplicationContext(),MsgList);
        MainList=(RecyclerView)findViewById(R.id.usr_msgList);
        MainList.setHasFixedSize(true);
        MainList.setLayoutManager(new LinearLayoutManager(this));
        MainList.setAdapter(usrMsgListAdapter);
        final String TAG="FireLog";


        mFirestore=FirebaseFirestore.getInstance();
        mFirestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot documentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(e !=null)
                {
                    Log.d(TAG,"Error: "+e.getMessage());
                    return;
                }


                for(DocumentChange doc : documentSnapshots.getDocumentChanges())
                {
                    if(doc.getType()==DocumentChange.Type.ADDED)
                    {
                        String user_id=doc.getDocument().getId();
                        UsrMsgData msgs=doc.getDocument().toObject(UsrMsgData.class).withId(user_id);
                        MsgList.add(msgs);
                        usrMsgListAdapter.notifyDataSetChanged();


                    }
                }





            }
        });

    }
}

