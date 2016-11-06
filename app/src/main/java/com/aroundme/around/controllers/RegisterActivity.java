package com.aroundme.around.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aroundme.around.R;
import com.aroundme.around.controllers.MainActivity;
import com.aroundme.around.models.User;
import com.aroundme.around.models.UserAdder;
import com.aroundme.around.models.UserLoader;

public class RegisterActivity extends AppCompatActivity {

    EditText first_name;
    EditText last_name;
    EditText email;
    EditText password;
    Button submit_button;
    TextView cancel_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        submit_button = (Button) findViewById(R.id.submit_button);
        cancel_button = (TextView) findViewById(R.id.cancel_button);
    }

    public void handleSubmitClicked(View view) {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userFirst = first_name.getText().toString();
        String userLast = last_name.getText().toString();
        SharedPreferences sp = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        Holder.user = userEmail;
        new UserAdder().execute(userFirst, userLast, userEmail, userPassword);
        new UserLoader().execute(userEmail, userPassword);
        editor.putBoolean("login", true);
        Intent viewMain = new Intent(this, MainActivity.class);
        startActivity(viewMain);

    }

    public void handleCancelClicked(View view) {
        super.onBackPressed();
    }
}
