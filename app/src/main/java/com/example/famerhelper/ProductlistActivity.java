package com.example.famerhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ProductlistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

        TextView title_main = findViewById(R.id.title_name_productlist);
        if (this.getIntent().hasExtra("username")) {
            title_main.setText("Hi " + this.getIntent().getStringExtra("username"));
        }else{
            title_main.setText("Home");
        }

        // get database ref
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("test");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("fh_prefs", Context.MODE_PRIVATE);
        boolean is_owner = pref.getBoolean("is_owner", false);

        Button button_addmore = (Button) findViewById(R.id.btn_add);
        button_addmore.setEnabled(is_owner);
        button_addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.activitytest.ACTION_ADD");
                intent.addCategory("com.example.activitytest.ADD");
                startActivity(intent);
            }
        });

        Button button_logout = (Button) findViewById(R.id.logOut);
        button_logout.setOnClickListener(v -> startActivity(new Intent(ProductlistActivity.this, WelcomeActivity.class)));

        Query usernameQuery = myRef.child("products");
        usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot: snapshot.getChildren()) {
                    ProductInfo product = userSnapshot.getValue(ProductInfo.class);
                    assert product != null;
                    readProduct(product, storage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException(); // never ignore errors
            }
        });
    }

    public void readProduct(ProductInfo productInfo, FirebaseStorage storage){
        String product_id = productInfo.getImage();

        // imageButton
        StorageReference image_storage = storage.getReferenceFromUrl("gs://farmerhelper-c40a2.appspot.com").child(product_id);
        ImageButton imageButton = new ImageButton(this);

        image_storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(imageButton);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                System.out.println("Error: download image error");
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.activitytest.ACTION_PRODUCTSPEC");
                intent.addCategory("com.example.activitytest.PRODUCTSPEC");
                Bundle bundle = new Bundle();
                bundle.putString("product_id", product_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        TableRow tableRow = new TableRow(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        params.setMargins(0,0,0,10);
        tableRow.addView(imageButton);

        LinearLayout linearLayout = findViewById(R.id.product_list_layout);
        linearLayout.addView(tableRow);
    }

}