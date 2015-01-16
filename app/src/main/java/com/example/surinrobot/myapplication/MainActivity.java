package com.example.surinrobot.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    private ImageView img;
    private Bitmap res;
    private MenuItem acb;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        img = (ImageView) findViewById(R.id.imageView);
        GetBitMapURL get = new GetBitMapURL();
        get.execute("http://shop.surinrobot.com/stock/logo/size_128px/2013-09-30_00-56-53_logo_524869e5454beimages.jpg");
    }

    public void changetitle(View view) {
        acb = menu.findItem(R.id.action_mode_bar_stub);
        acb.setTitle("ddddd");
    }

    private class GetBitMapURL extends AsyncTask <String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL strurl = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) strurl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream input = conn.getInputStream();
                res = BitmapFactory.decodeStream(input);


            } catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    img.setImageBitmap(res);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,MainActivity2.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
