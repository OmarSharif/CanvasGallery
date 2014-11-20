package com.example.devtest;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    private Button galleryBtn;

    //private TextView introTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            galleryBtn = (Button) findViewById(R.id.galleryBtn);
            galleryBtn.setOnClickListener(new OnClickListener() {

                public void onClick(View view) {

                    Intent i = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, 1);

                }

            });

        }

    }
}
