package com.example.devtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CanvasFrag extends Fragment implements OnTouchListener {
    private ImageView imgView;
    Bitmap bmp;
    Canvas canvas;
    float downx = 0;
    float downy = 0;
    float upx = 0;
    float upy = 0;
    Paint paint;
    Context context;
    Bitmap alteredBitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String picturePath = this.getArguments().getString("image");
        Uri imageUri = Uri.parse(this.getArguments().getString("imageUri"));

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.canvas, container, false);
        imgView = (ImageView) view.findViewById(R.id.imgView);
        
        try{
            BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
            bmpFactoryOptions.inJustDecodeBounds = true;
            bmp = BitmapFactory.decodeFile(picturePath, bmpFactoryOptions);

            bmpFactoryOptions.inJustDecodeBounds = false;
            bmp = BitmapFactory.decodeFile(picturePath, bmpFactoryOptions);
            alteredBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
            canvas = new Canvas(alteredBitmap);
            paint = new Paint();
            paint.setColor(Color.GREEN);
            Matrix matrix = new Matrix();
            canvas.drawBitmap(bmp, matrix, paint);
            imgView.setImageBitmap(alteredBitmap);
            imgView.setOnTouchListener(this);


        } catch (Exception e) {
            Log.v("ERROR", e.toString());
        }
        
        
       // imgView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        return view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
        case MotionEvent.ACTION_DOWN:
          downx = event.getX();
          downy = event.getY();
          break;
        case MotionEvent.ACTION_MOVE:
          upx = event.getX();
          upy = event.getY();
          canvas.drawLine(downx, downy, upx, upy, paint);
          imgView.invalidate();
          downx = upx;
          downy = upy;
          break;
        case MotionEvent.ACTION_UP:
          upx = event.getX();
          upy = event.getY();
          canvas.drawLine(downx, downy, upx, upy, paint);
          imgView.invalidate();
          break;
        case MotionEvent.ACTION_CANCEL:
          break;
        default:
          break;
        }
        return true;
      }
    
}
