package com.example.famerhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("fh_prefs", Context.MODE_PRIVATE);
        boolean is_owner = pref.getBoolean("is_owner", false);

        TextView title_main = findViewById(R.id.title_main);
        if (this.getIntent().hasExtra("username")) {
            title_main.setText("Hi " + this.getIntent().getStringExtra("username"));
        }

        Button btn_add = (Button) findViewById(R.id.button_1);
        btn_add.setEnabled(is_owner);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button 1 is clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
    }
}