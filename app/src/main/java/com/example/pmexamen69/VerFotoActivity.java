package com.example.pmexamen69;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.graphics.Bitmap;
public class VerFotoActivity extends AppCompatActivity {

    ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_foto);

        imageView2=findViewById(R.id.imageView2);
        byte[] contactPhoto= getIntent().getByteArrayExtra("contactPhoto");
        if(contactPhoto!=null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(contactPhoto, 0, contactPhoto.length);
            imageView2.setImageBitmap(bitmap);
        }
    }
}