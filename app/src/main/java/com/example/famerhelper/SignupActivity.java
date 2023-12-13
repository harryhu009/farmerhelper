package com.example.famerhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

  Button btn_submit, btn_cancel;
  EditText input_username, input_password, input_email,input_firstname, input_familyname;
  Switch isOwner;
  Intent loginInfo;
  int userId;
  DatabaseReference mDatabase;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);
    mDatabase = FirebaseDatabase.getInstance().getReference();
    userId = 1;
    mDatabase.child("userInfo").addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
        userId++;
      }

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {}

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

      @Override
      public void onCancelled(DatabaseError databaseError) {}
    });

    btn_submit = (Button) findViewById(R.id.btn_submit);
    btn_cancel = (Button) findViewById(R.id.btn_cancel);
    input_username = (EditText) findViewById(R.id.input_username);
    input_password = (EditText) findViewById(R.id.input_password);
    input_email = (EditText) findViewById(R.id.input_email);
    input_firstname = (EditText) findViewById(R.id.input_firstname);
    input_familyname = (EditText) findViewById(R.id.input_familyname);
    isOwner = (Switch) findViewById(R.id.isOwner);

    btn_submit.setEnabled(false);
    input_username.addTextChangedListener(enableSubmit);
    input_password.addTextChangedListener(enableSubmit);

    loginInfo = new Intent(SignupActivity.this, LoginActivity.class);

    btn_submit.setOnClickListener(v -> submitUserData());
    btn_cancel.setOnClickListener(v -> startActivity(new Intent(SignupActivity.this, WelcomeActivity.class)));
  }

  private void submitUserData () {
    System.out.println(isOwner.isChecked());
    User userInfo = new User(input_username.getText().toString(), input_password.getText().toString(),
            input_email.getText().toString(), isOwner.isChecked(),
            input_firstname.getText().toString(),input_familyname.getText().toString());

    //DatabaseReference userInfoRef = database.getReference("userInfo");

    mDatabase.child("userInfo").child(String.valueOf(userId)).setValue(userInfo);
    startActivity(loginInfo);
  }

  private final TextWatcher enableSubmit = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
      btn_submit.setEnabled(input_password.getText().toString().length() != 0 && input_username.getText().toString().length() != 0);
      loginInfo.putExtra("username", input_username.getText().toString());
      loginInfo.putExtra("password", input_password.getText().toString());
    }
  };
}