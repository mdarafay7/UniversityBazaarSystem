package com.example.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Messaging extends AppCompatActivity {

    private Button single;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        single=findViewById(R.id.Single);

        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Messaging.this, UsrMsg.class);
                startActivity(intent);
            }
        });
    }
    //TextView myAwesomeTextView = (TextView)findViewById(R.id.textView2);
    //myAwesomeTextView.setText("University Bazaar System");
}
