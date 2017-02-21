package com.example.sushma.netlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sushma on 2/18/17.
 * Main Activity used to test the working of network dispatchers
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        makeNetworkCall();

    }

    public void makeNetworkCall() {
        NetLib netLibInstance = NetLib.getInstance();
        int i=0;
        URL url = null;
        try {
            url = new URL("https://www.google.com/maps/@37.3691062,-121.9984934,15z?hl=en");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        while(i < 20) {
            try {
                netLibInstance.addToQueue(new Request("Request No " + i, Request.Method.GET, url));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
