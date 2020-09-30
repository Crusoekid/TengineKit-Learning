package com.tenginekit.imagehandle;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.tenginekit.AndroidConfig;
import com.tenginekit.Image;
import com.tenginekit.KitCore;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Bitmap OriginBitmap = null;
    private ImageView handleResultImageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OriginBitmap = getImage();
        initView();
        KitCore.init(this, AndroidConfig.create());
    }

    private void initView(){
        ((ImageView)findViewById(R.id.originImg)).setImageBitmap(OriginBitmap);
        handleResultImageView = (ImageView)findViewById(R.id.afterImg);

        findViewById(R.id.btn_90).setOnClickListener(this);
        findViewById(R.id.btn_180).setOnClickListener(this);
        findViewById(R.id.btn_270).setOnClickListener(this);
        findViewById(R.id.btn_cut).setOnClickListener(this);
        findViewById(R.id.btn_resize).setOnClickListener(this);
        findViewById(R.id.btn_mirror).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_90:
                Bitmap result = Rotate90();
                handleResultImageView.setImageBitmap(result);
                break;
            case R.id.btn_180:
                Bitmap resut2 = Rotate180();
                handleResultImageView.setImageBitmap(resut2);
                break;
            case R.id.btn_270:
                Bitmap result3 = Rotate270();
                handleResultImageView.setImageBitmap(result3);
                break;
            case R.id.btn_cut:
                Bitmap result4 = Cut();
                handleResultImageView.setImageBitmap(result4);
                break;
            case R.id.btn_resize:
                Bitmap result5 = Resize();
                handleResultImageView.setImageBitmap(result5);
                break;
            case R.id.btn_mirror:
                Bitmap result6 = Mirror();
                handleResultImageView.setImageBitmap(result6);
                break;
        }
    }

    private Bitmap getImage(){
        Bitmap bitmap = null;
        AssetManager assetManager = this.getAssets();
        try {
            InputStream inputStream = assetManager.open("horse.jpg");//filename是assets目录下的图片名
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private Bitmap Rotate90(){
        int width = OriginBitmap.getWidth();
        int height = OriginBitmap.getHeight();
        return Image.convertImage(OriginBitmap, 0,0, width, height,width, height, 90, false);
    }

    private Bitmap Rotate180(){
        int width = OriginBitmap.getWidth();
        int height = OriginBitmap.getHeight();
        return Image.convertImage(OriginBitmap, 0,0, width, height,width, height, 180, false);
    }

    private Bitmap Rotate270(){
        int width = OriginBitmap.getWidth();
        int height = OriginBitmap.getHeight();
        return Image.convertImage(OriginBitmap, 0,0, width, height,width, height, 270, false);
    }

    private Bitmap Cut(){
        /**
         * 300,360 为图片左上角坐标
         * 720，1000 为图片右下角坐标
         * */
        return Image.convertImage(OriginBitmap, 300,360, 720, 1000, 420, 640, 0, false);
    }

    private Bitmap Resize(){
        int width = OriginBitmap.getWidth();
        int height = OriginBitmap.getHeight();
        return Image.convertImage(OriginBitmap, 0,0, width, height,width / 2, height / 2, 0, false);
    }

    private Bitmap Mirror(){
        int width = OriginBitmap.getWidth();
        int height = OriginBitmap.getHeight();
        return Image.convertImage(OriginBitmap, 0,0, width, height,width, height, 0, true);
    }
}
