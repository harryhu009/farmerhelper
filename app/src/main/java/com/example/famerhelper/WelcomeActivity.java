package com.example.famerhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);

    Button btn_login = (Button) findViewById(R.id.btn_login);
    Button btn_signup = (Button) findViewById(R.id.btn_signup);

    btn_signup.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, SignupActivity.class)));
    btn_login.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, LoginActivity.class)));
  }
}