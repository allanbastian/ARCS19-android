package android.ieeecsvit.com.arcs19;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URL;

public class RegisterWebView extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;

    String URL = "https://register.ieeecsvit.com/login";

    SharedPreferences sp;
    String email, password;

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

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSaveFormData(false);
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
                String js = "javascript:function(){" +
                        "document.getElementById('email').value='"+email+"';" +
                        "document.getElementById('pwd').value = '" + password +"';" +
                        "document.getElementByClassName('waves-effect btn btncolor').click()}";

                webView.evaluateJavascript(js, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {


                    }
                });

            }
        });

        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });

        webView.loadUrl(URL);
        //webView.addJavascriptInterface(new JavaScriptInterface(this), "Android");

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
