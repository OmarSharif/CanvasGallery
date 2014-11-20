package com.example.devtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
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
    
    //Picture pic;

    private Button greenBtn;

    private Button blueBtn;

    private Button redBtn;

    private Button grayBtn;

    private Button blackBtn;
    
    private Button saveBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String picturePath = this.getArguments().getString("image");
        // Uri imageUri = Uri.parse(this.getArguments().getString("imageUri"));
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.canvas, container, false);
        greenBtn = (Button) view.findViewById(R.id.greenBtn);
        blueBtn = (Button) view.findViewById(R.id.blueBtn);
        redBtn = (Button) view.findViewById(R.id.redBtn);
        grayBtn = (Button) view.findViewById(R.id.grayBtn);
        blackBtn = (Button) view.findViewById(R.id.blackBtn);
        saveBtn = (Button) view.findViewById(R.id.saveBtn);


        imgView = (ImageView) view.findViewById(R.id.imgView);
        paint = new Paint();
       // pic = new Picture();

        //This will set the paint to green when btn is clicked
        greenBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                paint.setColor(Color.GREEN);

            }
        });
        //This will set the paint to blue when btn clicked
        blueBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                paint.setColor(Color.BLUE);

            }
        });
        //This will set the paint to red when btn clicked
        redBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                paint.setColor(Color.RED);

            }
        });
        //This will set the paint to gray when btn clicked
        grayBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                paint.setColor(Color.GRAY);

            }
        });
        //This will set the paint to black when btn clicked
        blackBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                paint.setColor(Color.BLACK);
                //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                //canvas.drawLine(storeDownx(downx), storeDowny(downy), storeUpx(upx), storeUpy(upy), paint);
                

            }
        });
        try {
            BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
            bmpFactoryOptions.inJustDecodeBounds = true;
            bmp = BitmapFactory.decodeFile(picturePath, bmpFactoryOptions);

            bmpFactoryOptions.inJustDecodeBounds = false;
            bmp = BitmapFactory.decodeFile(picturePath, bmpFactoryOptions);
            alteredBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
            canvas = new Canvas(alteredBitmap);
            Matrix matrix = new Matrix();
            canvas.drawBitmap(bmp, matrix, paint);
            imgView.setImageBitmap(alteredBitmap);
            imgView.setOnTouchListener(this);
           // pic.draw(canvas);
           // pic.beginRecording(bmp.getWidth(), bmp.getHeight());


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
            //storeDowny(downy);
            //storeDownx(downx);
            //storeUpy(upx);
            //storeUpx(upy);
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
    /*Attempt to store line coordinates to create redrawing effect
    public void storeLines(float downx, float downy, float upx, float upy){
        this.downx = downx;
        this.downy = downy;
        this.upx = upx;
        this.upy= upy;
    }
    
    public float storeDowny(float downy){
        this.downy = downy;
        return downy;
    }
    public float storeUpx(float upx){
        this.upx = upx;
        return upx;
    }
    public float storeUpy(float upy){
        this.upy = upy;
        return upy;
    }
    public float storeDownx(float downx){
        this.downx = downx;
        return downx;
    }
    
    public float getDownx(){
        return downx;
        
    }
    public float getDowny(){
        return downy;
        
    }
    public float getUpx(){
        return downx;
        
    }
    public float getUpy(){
        return downx;
        
    }*/

}
