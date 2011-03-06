/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jl.surfosd;

import android.app.*;
import android.os.Bundle;
import android.webkit.WebView;
import android.view.KeyEvent;
import android.view.View.OnLongClickListener;
import android.view.View;
import android.widget.Toast;

/**
 *
 * @author jens
 */
public class ReadForumActivity extends Activity implements OnLongClickListener {

    WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mywebview);
        //WebView mWebView = (WebView) findViewById(R.id.webview);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://surfosd.se/392-17-83.html");//http://surfosd.se/392-17-83.html
        //setContentView(mWebView);
        mWebView.setOnLongClickListener(this);
        mWebView.setWebViewClient(new SurfOsdWebViewClient());
       
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

     public boolean onLongClick(View  v) {

            Toast.makeText(getBaseContext(),"Sidan Laddas nu om!",Toast.LENGTH_LONG).show();
            mWebView.reload();

            return true;
        
    }
}
