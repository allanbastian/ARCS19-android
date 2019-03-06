package com.ieeecsvit.arcs19;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

public class RegisterWebView extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;

    String URL = "https://register.ieeecsvit.com/api/login-webview?ename=";
    String eventName;

    SharedPreferences sp;
    String email, password,jwtToken;

    Map<String,String> header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.web_view);
        progressBar = findViewById(R.id.progress_bar);

        progressBar.setMax(100);

        sp = getSharedPreferences("key",0);
        email = sp.getString("email","");
        password = sp.getString("password","");
        jwtToken = sp.getString("jwtToken","");

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            eventName = bundle.getString("eventName");
            Log.e("EventName",eventName);
        }


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSaveFormData(false);
        webSettings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }



            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);

            }
        });

        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });

        header = new HashMap<String,String>();
        header.put("token",jwtToken);
        URL = URL+eventName;
        webView.loadUrl(URL, header);


    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else {
            finish();
        }
    }
}