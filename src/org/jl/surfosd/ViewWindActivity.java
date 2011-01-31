/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jl.surfosd;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.view.KeyEvent;
import android.view.View.OnFocusChangeListener;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnLongClickListener;

/**
 *
 * @author jens
 */
public class ViewWindActivity extends Activity implements OnFocusChangeListener, OnLongClickListener {//

    WebView mWebView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here
        setContentView(R.layout.mywebview);
        //WebView mWebView = (WebView) findViewById(R.id.webview);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://salongnobless.se/vader/");//http://surfosd.se/392-17-83.html
        //setContentView(mWebView);
        mWebView.setOnLongClickListener(this);

        mWebView.setWebViewClient(new SurfOsdWebViewClient());
        mWebView.setOnFocusChangeListener(this);
        
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onResume() {
        super.onResume();

        mWebView.reload();
    }

    
    public void onFocusChange(View v, boolean b) {

        
        mWebView.loadUrl("http://salongnobless.se/vader/");
        if (b){// (mWebView.isShown()){
            //Toast.makeText(getBaseContext(),"Hello, Wind!",Toast.LENGTH_LONG).show();
            mWebView.reload();
        }
            
    }

    public boolean onLongClick(View  v) {

            Toast.makeText(getBaseContext(),"Sidan Laddas nu om!",Toast.LENGTH_LONG).show();
            mWebView.reload();

            return true;

    }
}
