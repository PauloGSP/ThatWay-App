package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

public class MapSelectLocation extends AppCompatActivity {

    public static Trip trip;
    public static boolean isDeparture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_map_select_location2);
        WebView webview = (WebView) findViewById(R.id.WebView);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);


        if(isDeparture){
            ArrayList<String> coordparse= new ArrayList<String>();
            for (String i : trip.getOrigin_coords().split(",")){
                coordparse.add(i);
            }

            webview.loadUrl("https://www.openstreetmap.org/#map=17/"+coordparse.get(0)+"/"+coordparse.get(1));

        }else {
            ArrayList<String> coordparse= new ArrayList<String>();
            for (String i : trip.getOrigin_coords().split(",")){
                coordparse.add(i);
            }

            webview.loadUrl("https://www.openstreetmap.org/#map=17/"+coordparse.get(0)+"/"+coordparse.get(1));

        }
        //exemplo de url https://www.google.pt/maps/@40.6331114,-8.655595,21z
    }
}