package com.example.hope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import static com.example.hope.MainActivity.userid;

public class updateUserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
    }
    public void btReturn(View view){
        Intent returnIntent = new Intent(updateUserInfo.this,UserAreaActivity.class);
        updateUserInfo.this.startActivity(returnIntent);
    }
    public void btSave(View view){
        DBConnection db = new DBConnection(this);

        final EditText editName = (EditText) findViewById(R.id.edit_name);
        final EditText editUserName = (EditText) findViewById(R.id.edit_userName);
        final EditText editAge = (EditText) findViewById(R.id.edit_Age);
        final EditText editPassword = (EditText) findViewById(R.id.edit_password);
        if(!editName.getText().toString().equals("")){
        db.updateName(editName.getText().toString(),Integer.parseInt(userid));
            Intent registerIntent = new Intent(updateUserInfo.this,MainActivity.class);
            updateUserInfo.this.startActivity(registerIntent);

        }
        if (!editAge.getText().toString().equals("")){
            db.updateAge(editAge.getText().toString(),Integer.parseInt(userid));
            Intent registerIntent = new Intent(updateUserInfo.this,MainActivity.class);
            updateUserInfo.this.startActivity(registerIntent);
        }
        if (!editUserName.getText().toString().equals("")){
            db.updateUserName(editUserName.getText().toString(),Integer.parseInt(userid));
            Intent registerIntent = new Intent(updateUserInfo.this,MainActivity.class);
            updateUserInfo.this.startActivity(registerIntent);
        }if (!editPassword.getText().toString().equals("")){
            db.updatePassword(editPassword.getText().toString(),Integer.parseInt(userid));
            Intent registerIntent = new Intent(updateUserInfo.this,MainActivity.class);
            updateUserInfo.this.startActivity(registerIntent);
        }



    }

}
