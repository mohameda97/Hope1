package com.example.hope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

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

    }
}
