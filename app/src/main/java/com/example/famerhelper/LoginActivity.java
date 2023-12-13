package com.example.famerhelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

  EditText input_username, input_password;
  Button btn_login;
  Intent main_intent;
  DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("userInfo");
  DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("test");

  private final TextWatcher enable_button = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
      btn_login.setEnabled(input_password.getText().toString().length() != 0 && input_username.getText().toString().length() != 0);
    }
  };

  private boolean check_cred(ArrayList<Object> users, String username, String password) {
    HashMap<String, String> credentials = new HashMap<>();
    HashMap<String, Boolean> is_owner_map = new HashMap<>();

    for (int i = 1; i < users.size(); i++) {
      HashMap<String, Object> curr_user = (HashMap<String, Object>) users.get(i);
      if (curr_user != null) {
        credentials.put(String.valueOf(curr_user.get("username")), String.valueOf(curr_user.get("password")));
        is_owner_map.put(String.valueOf(curr_user.get("username")), (Boolean) curr_user.get("isOwner"));
        if (Objects.equals(curr_user.get("username"), username)) {
          main_intent.putExtra("username", username);
        }
      }
    }

    // if login success, also set the global variable is_owner
    if (Objects.equals(credentials.get(username), password)) {
      SharedPreferences prefs = getApplicationContext().getSharedPreferences("fh_prefs", Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = prefs.edit();
      editor.putBoolean("is_owner", Boolean.TRUE.equals(is_owner_map.get(username)));
      editor.apply();
    }

    return Objects.equals(credentials.get(username), password);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    btn_login = (Button) findViewById(R.id.btn_login_login);
    input_username = (EditText) findViewById(R.id.input_login_username);
    input_password = (EditText) findViewById(R.id.input_login_password);

    Query usernameQuery = myRef.child("products");
    usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.exists()) {
          main_intent = new Intent(LoginActivity.this, ProductlistActivity.class);
        }
        else {
          main_intent = new Intent(LoginActivity.this, MainActivity.class);
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        throw error.toException(); // never ignore errors
      }
    });

    btn_login.setEnabled(false);

    // btn_login is only enabled when both input_username and input_password not empty
    input_password.addTextChangedListener(enable_button);
    input_username.addTextChangedListener(enable_button);

    // get username and email from signup if exists
    if (this.getIntent().hasExtra("username")) {
      input_username.setText(this.getIntent().getStringExtra("username"));
    }
    if (this.getIntent().hasExtra("password")) {
      input_password.setText(this.getIntent().getStringExtra("password"));
    }

    //    btn_login.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));
    btn_login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        db.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (check_cred((ArrayList<Object>) dataSnapshot.getValue(), input_username.getText().toString(), input_password.getText().toString())) {
              Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
              startActivity(main_intent);
            } else {
              Toast.makeText(getApplicationContext(), "You have entered an invalid username or password", Toast.LENGTH_LONG).show();
            }
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
          }
        });
      }
    });
  }
}