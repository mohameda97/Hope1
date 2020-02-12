package com.example.hope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import static com.example.hope.MainActivity.userid;

public class UserAreaActivity extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(UserAreaActivity.this,"Signed out!",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        final TextView etAge = (TextView) findViewById(R.id.etAge);
        final TextView etName = (TextView) findViewById(R.id.etName);
        final TextView etUser_name = (TextView) findViewById(R.id.etUser_name);
        final Button btLogout = (Button) findViewById(R.id.bLogout);
        final DBConnection db = new DBConnection(this);
        final ListView ls = findViewById(R.id.list_view);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
       if (userid==1) {
           ArrayList<String> arrayList = db.getAllrecord();
           ArrayAdapter<String> arrayAdapter =
                   new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
           ls.setAdapter(arrayAdapter);
       }
       else {
           ls.setVisibility(View.INVISIBLE);
       }
        etName.setText(db.getName(userid));
        etAge.setText(db.getAge(userid));
        etUser_name.setText(db.getUsername(userid));

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            signOut();
                Intent logoutIntent = new Intent(UserAreaActivity.this,MainActivity.class);
                UserAreaActivity.this.startActivity(logoutIntent);

            }
        });



    }


        public void btEdit(View view){
            Intent registerIntent = new Intent(UserAreaActivity.this,updateUserInfo.class);
            UserAreaActivity.this.startActivity(registerIntent);

    }
    public void btDelete(View view){
    DBConnection db = new DBConnection(this);
    db.deleteR(userid);

        Intent logoutIntent = new Intent(UserAreaActivity.this,MainActivity.class);
        UserAreaActivity.this.startActivity(logoutIntent);

    }



                }
