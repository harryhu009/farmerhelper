package com.example.famerhelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ProductSpecActivity extends AppCompatActivity {
    private boolean isEditMode;
    public String product_id;
    public String name;
    public String description;
    public float stock;
    public float price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productspec);

        // components
        ViewSwitcher name_switcher = (ViewSwitcher) findViewById(R.id.name_switcher);
        ViewSwitcher description_switcher = (ViewSwitcher) findViewById(R.id.description_switcher);
        ViewSwitcher stock_switcher = (ViewSwitcher) findViewById(R.id.stock_switcher);
        ViewSwitcher price_switcher = (ViewSwitcher) findViewById(R.id.price_switcher);

        TextView nameTV = (TextView)findViewById(R.id.name_text);
        TextView descriptionTV = (TextView)findViewById(R.id.description_text);
        TextView stockTV = (TextView)findViewById(R.id.stock_text);
        TextView priceTV = (TextView)findViewById(R.id.price_text);
        ImageView imageIV = (ImageView)findViewById(R.id.imageView);

        EditText nameET = (EditText)findViewById(R.id.hidden_name_edit);
        EditText descriptionET = (EditText)findViewById(R.id.hidden_description_edit);
        EditText stockET = (EditText)findViewById(R.id.hidden_stock_edit);
        EditText priceET = (EditText)findViewById(R.id.hidden_price_edit);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("fh_prefs", Context.MODE_PRIVATE);
        boolean is_owner = pref.getBoolean("is_owner", false);

        product_id = getIntent().getExtras().getString("product_id");

        // connect database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("test");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference image_storage = storage.getReferenceFromUrl("gs://farmerhelper-c40a2.appspot.com").child(product_id);
        image_storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(imageIV);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                System.out.println("Error: download image error");
            }
        });

        Query productQuery = myRef.child("products").child(product_id);
        isEditMode = false;
        productQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProductInfo productInfo = snapshot.getValue(ProductInfo.class);

                name = productInfo.getHasType();
                description = productInfo.getDescription();
                stock = productInfo.getTotalTheoriticalStock();
                price = productInfo.getPrice();

                nameTV.setText(name);
                descriptionTV.setText(description);
                stockTV.setText(String.valueOf(stock));
                priceTV.setText(String.valueOf(price));

                nameET.setText(name);
                descriptionET.setText(description);
                stockET.setText(String.valueOf(stock));
                priceET.setText(String.valueOf(price));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });

        Button editButton = (Button) findViewById(R.id.edit_spec);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEditMode){
                    name_switcher.showPrevious();
                    description_switcher.showPrevious();
                    stock_switcher.showPrevious();
                    price_switcher.showPrevious();

                    name = nameET.getText().toString();
                    description = descriptionET.getText().toString();
                    stock = Float.parseFloat(stockET.getText().toString());
                    price = Float.parseFloat(priceET.getText().toString());

                    nameTV.setText(name);
                    descriptionTV.setText(description);
                    stockTV.setText(String.valueOf(stock));
                    priceTV.setText(String.valueOf(price));

                    editButton.setText("Edit");

                    ProductInfo product = new ProductInfo(name,description, stock, price, product_id);
                    myRef.child("products").child(product_id).setValue(product);
                }else{
                    name_switcher.showNext();
                    description_switcher.showNext();
                    stock_switcher.showNext();
                    price_switcher.showNext();

                    editButton.setText("Save");
                }
                isEditMode = !isEditMode;
            }
        });

        Button backButton = (Button) findViewById(R.id.spec_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.activitytest.ACTION_BACK");
                intent.addCategory("com.example.activitytest.BACK");
                startActivity(intent);
            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete_product);
        deleteButton.setEnabled(is_owner);
        deleteButton.setBackgroundColor(Color.TRANSPARENT);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child("products").child(product_id).setValue(null);
                Intent intent = new Intent("com.example.activitytest.ACTION_BACK");
                intent.addCategory("com.example.activitytest.BACK");
                startActivity(intent);
            }
        });
    }
}
