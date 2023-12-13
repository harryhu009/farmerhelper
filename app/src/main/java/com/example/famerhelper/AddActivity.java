package com.example.famerhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidObjectException;

public class AddActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText name;
    private EditText description;
    private EditText stock;
    private EditText price;
    private SharedPreferences.Editor editor;
    private Uri photo_uri = null;

    //firebase
    FirebaseStorage storage = FirebaseStorage.getInstance();
    // Create a storage reference from our app
    StorageReference storageRef = storage.getReferenceFromUrl("gs://farmerhelper-c40a2.appspot.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        // FIXME: dropdown for stock
//        Spinner dropdown_stock = findViewById(R.id.dropdown_stock);
//        String[] weight_unit = new String[]{"KG", "LB", "T"};
//        ArrayAdapter<String> adapter_stock = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, weight_unit);
//        dropdown_stock.setAdapter(adapter_stock);

        imageView = findViewById(R.id.picture_set);

        Button button_add_picture = (Button) findViewById(R.id.button_picture);
        button_add_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPicture();
            }
        });

        Button button_submit = (Button) findViewById(R.id.button_submit);
        name = (EditText) findViewById(R.id.input_product_name);
        description = (EditText) findViewById(R.id.input_prod_description);

        stock = (EditText) findViewById(R.id.input_stock);
        price = (EditText) findViewById(R.id.input_price);


        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("test");

                //product id
                String product_id = myRef.push().getKey();
                if (product_id == null){
                    product_id = "error id";
                }

                String input_name = name.getText().toString();
                String input_description = description.getText().toString();
                String input_stock_string =stock.getText().toString();

                String input_price_string = price.getText().toString();

                String image_id = "";

                if(photo_uri == null || input_name.equals("") || input_description.equals("") || input_stock_string.equals("") || input_price_string.equals("")){
                    Toast.makeText(AddActivity.this, "Please input all fields", Toast.LENGTH_SHORT).show();
                }else{
                        StorageReference childRef = storageRef.child(product_id);
                        //uploading the image
                        UploadTask uploadTask = childRef.putFile(photo_uri);
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                Toast.makeText(AddActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(AddActivity.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                            }
                        });

                    float input_stock = Float.parseFloat(input_stock_string);
                    float input_price = Float.parseFloat(input_price_string);
                    String stringUri;
                    if(photo_uri != null) {
                        stringUri = photo_uri.toString();
                    }else{
                        stringUri = "";
                    }
                    editor = getSharedPreferences("data",
                            MODE_PRIVATE).edit();
                    editor.putString("hasType", input_name);
                    editor.putString("description", input_description);
                    editor.putFloat("totalTheoriticalStock",input_stock);
                    editor.putFloat("price",input_price);
                    editor.putString("image",stringUri);
                    //editor.putLong()
                    editor.apply();


                    //add product to database

                    //image id is same as product id
                    image_id = product_id;
                    ProductInfo product = new ProductInfo(input_name,input_description,
                            input_stock, input_price, image_id);

                    myRef.child("products").child(product_id).setValue(product);

                    //go to congrats page
                    Intent intent = new Intent("com.example.activitytest.ACTION_CONGRATS");
                    intent.addCategory("com.example.activitytest.CONGRATS");

                    intent.putExtra("extra_data",input_name);
                    startActivity(intent);
                }

            }
        });
    }



    private void selectPicture() {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
        intent.setType("image/*");//pick from all pictures
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1 && null != data) {
            decodeUri(data.getData());
            photo_uri = data.getData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void decodeUri(Uri uri) {

        ParcelFileDescriptor parcelFD = null;
        try {
            parcelFD = getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor imageSource = parcelFD.getFileDescriptor();

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(imageSource, null, o);

            // the new size we want to scale to
            final int REQUIRED_SIZE = 1024;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(imageSource, null, o2);

            imageView.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (parcelFD != null)
                try {
                    parcelFD.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}