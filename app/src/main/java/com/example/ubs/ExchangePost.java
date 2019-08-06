package com.example.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ExchangePost extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String UID,UsersName;
    final String TAG="FireLog";
    private Button post;
    private FirebaseFirestore mFirestore;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference ref=db.collection("Users");
    private EditText message;
    private String to_Str;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        mFirestore=FirebaseFirestore.getInstance();
        message=findViewById(R.id.Message);
        post=findViewById(R.id.post_btn);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=message.getText().toString();
                Map<String,String> MessageMap=new HashMap<>();
                MessageMap.put("CONTENT",msg);
                MessageMap.put("TAGGER","A");


                mFirestore.collection("Exchange").add(MessageMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(ExchangePost.this,"Msg Sent",Toast.LENGTH_LONG).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error=e.getMessage();
                        Toast.makeText(ExchangePost.this,"ERROR"+error,Toast.LENGTH_LONG).show();
                    }
                });
                Intent intent = new Intent(ExchangePost.this, MainActivity.class);
                startActivity(intent);



            }



        });





    }
}
