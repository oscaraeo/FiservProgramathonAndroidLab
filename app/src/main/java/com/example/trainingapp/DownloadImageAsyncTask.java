package com.example.trainingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;

public class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private WeakReference<ImageView> avatarImage;
    public DownloadImageAsyncTask(ImageView avatarImage) {
        this.avatarImage = new WeakReference<>(avatarImage);
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap bmp = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bmp;
    }
    protected void onPostExecute(Bitmap result) {
        avatarImage.get().setImageBitmap(result);
    }
}