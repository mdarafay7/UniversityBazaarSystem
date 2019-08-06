package com.example.ubs;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClubMsg extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String UID,UsersName,Club_msg;
    final String TAG="FireLog";
    private Button send;
    private FirebaseFirestore mFirestore;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference ref=db.collection("Users");
    private CollectionReference ref2=db.collection("Messages");
    private EditText message;
    private String to_Str;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_info);
        listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String>arrayList=new ArrayList<>();
        final TextView c_name=findViewById(R.id.Club_Name);
        mAuth=FirebaseAuth.getInstance();
        final FirebaseUser theUser = mAuth.getCurrentUser();
        if (theUser != null)
        {UID = theUser.getEmail().toString();}
        to_Str=getIntent().getStringExtra("CLUB_NAME");
        c_name.setText(to_Str);
        mFirestore = FirebaseFirestore.getInstance();
        ref2.whereEqualTo("TO",to_Str).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots)
                {
                    MsgSender getter = documentSnapshot.toObject(MsgSender.class);
                    Club_msg=getter.getCONTENT();
                    arrayList.add(Club_msg);

                }
                ArrayAdapter arrayAdapter=new ArrayAdapter(ClubMsg.this,android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(arrayAdapter);

            }
        });



        send=findViewById(R.id.btnPost);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.whereEqualTo("EMAIL", UID).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            MsgSender name = documentSnapshot.toObject(MsgSender.class);
                            //  name.setEmail(UID);
                            UsersName = name.getName();



                        }


                        mFirestore = FirebaseFirestore.getInstance();
                        message = findViewById(R.id.tvPostMsg);
                        String msg = message.getText().toString();
                        Map<String, String> MessageMap = new HashMap<>();
                        MessageMap.put("FROM", UsersName);
                        MessageMap.put("TO", to_Str);
                        MessageMap.put("CONTENT", msg);

                        mFirestore.collection("Messages").add(MessageMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ClubMsg.this, "Msg Sent", Toast.LENGTH_LONG).show();
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String error = e.getMessage();
                                Toast.makeText(ClubMsg.this, "ERROR" + error, Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });


            }


        });








    }
}
