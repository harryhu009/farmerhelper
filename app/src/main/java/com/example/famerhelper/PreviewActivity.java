package com.example.famerhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PreviewActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        imageView = findViewById(R.id.food_preview);

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        String name ="Name: " + pref.getString("hasType", "");
        String uri_string = pref.getString("image", "");
        String description = pref.getString("description", "");
        float input_stock = pref.getFloat("totalTheoriticalStock",0);
        String stock = "Stock: " + input_stock +" KG";
        float input_price = pref.getFloat("price",0);
        String price = "Price: " + input_price + " $";

        TextView name_view = (TextView) findViewById(R.id.t_preview_product_name);
        name_view.setText(name);

        TextView description_view = (TextView) findViewById(R.id.t_preview_product_description);
        description_view.setText(description);

        TextView stock_view = (TextView) findViewById(R.id.t_preview_stock);
        stock_view.setText(stock);

        TextView price_view = (TextView) findViewById(R.id.t_preview_price);
        price_view.setText(price);

        Uri uri;
        uri = Uri.parse(uri_string);
        decodeUri(uri);

        Button button_confirm = (Button) findViewById(R.id.btn_preview_confirm);
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.activitytest.ACTION_BACK");
                intent.addCategory("com.example.activitytest.BACK");
                startActivity(intent);
            }
        });

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