package com.example.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button Clubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,"Club has been added",Toast.LENGTH_LONG).show();

        Clubs=findViewById(R.id.Clubs);

        Clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Clubs.class);
                startActivity(intent);
            }
        });
        }
        //TextView myAwesomeTextView = (TextView)findViewById(R.id.textView2);
        //myAwesomeTextView.setText("University Bazaar System");
    }


