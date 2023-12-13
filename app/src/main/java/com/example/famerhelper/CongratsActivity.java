package com.example.famerhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CongratsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_congrats);

    TextView t_product_name = (TextView) findViewById(R.id.t_published1);
    Button btn_back = (Button) findViewById(R.id.btn_back);
    Button btn_preview = (Button) findViewById(R.id.btn_preview);

    if (this.getIntent().hasExtra("extra_data")) {
      t_product_name.setText(this.getIntent().getStringExtra("extra_data"));
    }

    t_product_name.setGravity(Gravity.CENTER);

    btn_back.setOnClickListener(v -> startActivity(new Intent(CongratsActivity.this, ProductlistActivity.class)));
    btn_preview.setOnClickListener(v -> startActivity(new Intent(CongratsActivity.this, PreviewActivity.class)));
  }
}