package com.example.hope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public  String userID (String username){
        DBConnection db = new DBConnection(this);
        String userID = db.getID(username);
        return userID;
    }
    public  static  String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etUser_name = (EditText) findViewById(R.id.etUser_name);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink =(TextView) findViewById(R.id.tvRegisterLink);
        final DBConnection db =new DBConnection(this);

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
