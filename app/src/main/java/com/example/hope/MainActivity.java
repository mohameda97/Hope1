package com.example.hope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;


            // ...
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            String personEmail = acct.getEmail();
             userid=userID(personEmail);
            if (userid>0){
                Intent intent = new Intent(MainActivity.this,UserAreaActivity.class);
                startActivity(intent);
            }
            else {
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
            }
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());

        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    public  int userID (String username){
        DBConnection db = new DBConnection(this);
        int userID = db.getID(username);
        return userID;
    }
    public  static  int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etUser_name = (EditText) findViewById(R.id.etUser_name);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink =(TextView) findViewById(R.id.tvRegisterLink);
        final DBConnection db =new DBConnection(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = etUser_name.getText().toString();
                String password = db.getPassword(name);

                if (!password.equals("")&& etPassword.getText().toString().equals(password)){
                    userid=userID(name);
                    Intent registerIntent = new Intent(MainActivity.this,UserAreaActivity.class);
                    MainActivity.this.startActivity(registerIntent);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Invalid user name or password",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }

            }
        });

    }





}
