package com.example.ubs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inbox extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String UID,UsersName,Club_msg,from;
    final String TAG="FireLog";
    private Button send;
    private FirebaseFirestore mFirestore;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference ref=db.collection("Users");
    private CollectionReference ref2=db.collection("Messages");
    private EditText message;
    private String to_Str;
    List<Map<String, String>> data = new ArrayList<Map<String, String>>();

    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        final List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser theUser = mAuth.getCurrentUser();
        if (theUser != null) {
            UID = theUser.getEmail().toString();
        }
        mAuth = FirebaseAuth.getInstance();

        ref.whereEqualTo("EMAIL",UID).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String data = "";
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    MsgSender name = documentSnapshot.toObject(MsgSender.class);
                    UsersName = name.getName();
                    break;
                }
            }
        });

        Toast.makeText(Inbox.this, UID, Toast.LENGTH_LONG).show();

        ref2.whereEqualTo("TO","Abdul Rafay").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int i=0;
                HashMap<String,String> item;

                for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots)
                {
                    MsgSender getter = documentSnapshot.toObject(MsgSender.class);
                    Club_msg=getter.getCONTENT();
                    from=getter.getFROM();
                    HashMap<String, String> datum = new HashMap<String, String>(2);
                    datum.put("First Line",from);
                    datum.put("Second Line",Club_msg);
                    data.add(datum);
                }

                SimpleAdapter adapter = new SimpleAdapter(Inbox.this, data,
                        android.R.layout.simple_list_item_2,
                        new String[] {"First Line", "Second Line" },
                        new int[] {android.R.id.text1, android.R.id.text2 });
                ListView listView = (ListView) findViewById(R.id.msg_listview);
                listView.setAdapter(adapter);


            }
        });

    }
    }