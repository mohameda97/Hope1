package com.example.hope;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class RegisterActivity extends AppCompatActivity  {
GoogleSignInClient mGoogleSignInClient;

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUser_name = (EditText) findViewById(R.id.etUser_name);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final   DBConnection db = new DBConnection(this);




        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etUser_name.getText().toString();
                boolean check = db.checkUsername(name);
                if(!check&&!etName.getText().toString().equals("")&&!etPassword.getText().toString().equals("")&&!etUser_name.getText().toString().equals("")&&!etAge.getText().toString().equals("")){
                   db.insertROWpatient(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()), etUser_name.getText().toString(), etPassword.getText().toString());
                   Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                   RegisterActivity.this.startActivity(loginIntent);
               }
                else if(check) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "please use different username",
                            Toast.LENGTH_SHORT);

                    toast.show();

                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "please fill empty fields",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
            }

        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            int iduser =userID(personEmail);
            if (iduser<1){
            etName.setText( personGivenName);
            etUser_name.setText(personEmail);
            }
        }



    }
    public  int userID (String username){
        DBConnection db = new DBConnection(this);
        int userID = db.getID(username);
        return userID;
    }


}
