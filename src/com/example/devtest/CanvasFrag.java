package com.example.devtest;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CanvasFrag extends Fragment {
    private ImageView imgView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String picturePath = this.getArguments().getString("image");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.canvas, container, false);
        imgView = (ImageView) view.findViewById(R.id.imgView);
        imgView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        return view;
    }
}
