package com.example.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Messaging extends AppCompatActivity {

    private Button single,inboxb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        single = findViewById(R.id.Single);

        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Messaging.this, UsrMsg.class);
                startActivity(intent);
            }
        });

        inboxb=findViewById(R.id.inbox_btn);
                inboxb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Messaging.this, Inbox.class);
                        startActivity(intent);
                    }
                });

    }
}
