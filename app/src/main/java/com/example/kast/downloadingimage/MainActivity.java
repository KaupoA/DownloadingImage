package com.example.kast.downloadingimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView downloadView;

    public void downloadButton(View view) {

        // http://digitalspyuk.cdnds.net/14/40/768x523/gallery_ustv-south-park-s02e03-chickenlover.jpg

        DownloadingImage task = new DownloadingImage();

        Bitmap myImage;

        try {

            myImage = task.execute("http://digitalspyuk.cdnds.net/14/40/768x523/gallery_ustv-south-park-s02e03-chickenlover.jpg").get(); // Executes DownladingImage task with that URL

            downloadView.setImageBitmap(myImage); // Add image to imageView

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadView = (ImageView)findViewById(R.id.downloadView);

    }

    public class DownloadingImage extends AsyncTask<String, Void, Bitmap> { // Bitmap is for images, DownloadingImage task extends AsyncTask which happens on the background thread

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]); // Converts the URL to the valid URL

                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Create a HTTP URL connection

                connection.connect(); // Connects to that URL

                InputStream inputStream = connection.getInputStream(); // Create an input stream, which gets the content from that URL - downloads image

                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream); // Converts data to image

                return myBitmap;

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

            return null;

        }
    }

}
