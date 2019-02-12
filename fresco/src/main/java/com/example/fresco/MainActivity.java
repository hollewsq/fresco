package com.example.fresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("http://image.hnol.net/c/2019-02/10/12/201902101251055161-239867.jpg");
        SimpleDraweeView img = findViewById(R.id.my_imgage);
        img.setImageURI(uri);

    }
}
