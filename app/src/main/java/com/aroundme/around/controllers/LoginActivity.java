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
import com.aroundme.around.models.UserLoader;

public class LoginActivity extends AppCompatActivity {


    EditText email;
    EditText password;
    Button login_button;
    TextView register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login_button = (Button) findViewById(R.id.login_button);
        register_button = (TextView) findViewById(R.id.register_button);
    }

    public void handleLoginClicked(View view) {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        if (userPassword.equals("pass")) {
            SharedPreferences sp = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
            Editor editor = sp.edit();
            editor.putString("email", userEmail);
            Intent mainView = new Intent(this, MainActivity.class);
            Holder.user = userEmail;
            new UserLoader().execute(userEmail, userPassword);
            startActivity(mainView);
        }

    }

    public void handleRegisterClicked(View view) {
        Intent registerView = new Intent(this, RegisterActivity.class);
        startActivity(registerView);
    }
}
