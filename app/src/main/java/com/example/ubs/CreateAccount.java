package com.example.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText Password;
    private Button CreateBtn;
    private TextView LoginLink;
    private Intent intent;

    private void createUser(String email, String pass){

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null ){
            intent = new Intent(CreateAccount.this,MainActivity.class);
            startActivity(intent);
        }

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("CREATEUSER", "createUserWithEmail:success");
                            intent = new Intent(CreateAccount.this,Login.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("CREATEUSER", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateAccount.this, "Creation failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Email = findViewById(R.id.etEmail);
        Password = findViewById(R.id.etPassword);

        CreateBtn = findViewById(R.id.btnCreate);
        LoginLink = findViewById(R.id.tvLoginLink);

        CreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String pass = Password.getText().toString();

                createUser(email, pass);
            }
        });

        LoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(CreateAccount.this,Login.class);
                startActivity(intent);
            }
        });

    }
}
