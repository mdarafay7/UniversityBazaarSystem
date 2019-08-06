package com.example.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button Clubs,Messaging,trade,exchange;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String UID,UsersName,Club_msg;
    final String TAG="FireLog";
    private Button send;
    private FirebaseFirestore mFirestore;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference ref=db.collection("Users");
    private CollectionReference ref2=db.collection("Exchange");

    private EditText message;
    private String to_Str;
    private TextView entryMsg;
    ListView listView;
    private List<UsrMsgData> MsgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,"Club has been added",Toast.LENGTH_LONG).show();
        final ArrayList<String> arrayList=new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();


        entryMsg=findViewById(R.id.textView);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser theUser = mAuth.getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();
        if (theUser != null)
        {UID = theUser.getEmail().toString();}

        String email="mdarafay@uta.edu";

        ref.whereEqualTo("EMAIL",email).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    MsgSender name = documentSnapshot.toObject(MsgSender.class);
                    UsersName = name.getName();

                }
            }

        });

        entryMsg.setText("Welcome "+UID);



        listView=findViewById(R.id.exchange_listView) ;
    ref2.whereEqualTo("TAGGER","A").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots)
                {
                    MsgSender getter = documentSnapshot.toObject(MsgSender.class);
                    Club_msg=getter.getCONTENT();
                    arrayList.add(Club_msg);

                }
                ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(arrayAdapter);

            }
        });


        Clubs=findViewById(R.id.Clubs);
        Messaging=findViewById(R.id.msg_btn);
        trade=findViewById(R.id.trade_btn);
        exchange=findViewById(R.id.exchange_btn);







        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExchangePost.class);
                startActivity(intent);
            }
        });
        Clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Clubs.class);
                startActivity(intent);
            }
        });


        Messaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Messaging.class);
                startActivity(intent);
            }
        });

        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, post.class);
                startActivity(intent);
            }
        });


    }
        //TextView myAwesomeTextView = (TextView)findViewById(R.id.textView2);
        //myAwesomeTextView.setText("University Bazaar System");
    }


