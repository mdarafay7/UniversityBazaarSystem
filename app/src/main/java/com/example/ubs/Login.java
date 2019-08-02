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

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;


    private Intent intent,crIntent;
    private EditText Username;
    private EditText Password;
    private Button Login;
    private TextView CreateLink;
    public String email;


    private void validate(String email, String pass){

        this.email = email;
        if (mAuth.getCurrentUser() != null ){
            intent = new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LOGIN", "signInWithEmail:success");
                            intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LOGIN", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    // validate with no toast
    private void stealthValidate(){

        if (mAuth.getCurrentUser() != null ){
            intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // REMOVE sign out on start REMOVE
        // mAuth.signOut();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.etUserName);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnLogin);
        CreateLink = findViewById(R.id.tvCreateLink);

        stealthValidate();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Username.getText().toString();
                String pass = Password.getText().toString();

                validate(user,pass);
            }
        });
        CreateLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crIntent = new Intent(Login.this,CreateAccount.class);
                startActivity(crIntent);
            }
        });

    }
}