/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jl.surfosd;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.view.KeyEvent;
import android.view.View.OnFocusChangeListener;
import android.view.View;
import android.widget.Toast;
import android.view.View.OnLongClickListener;

/**
 *
 * @author jens
 */
public class ForcastActivity extends Activity implements OnLongClickListener, OnFocusChangeListener {

    WebView mWebView;
    Calendar now;
    String tlater;
    String t;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here
        setContentView(R.layout.mywebview);

        setDates();

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://www.smhi.se/vadret/vadret-i-sverige/land/hourByHourTable.do?geonameid=2685750&starttime=" + t + "%2000:00:00&endtime=" + tlater + "%2000:00:00&redirect=false");//http://rl.se/vadret/grafik24c.php?stn=ESNZ
        mWebView.setOnFocusChangeListener(this);
        mWebView.setWebViewClient(new SurfOsdWebViewClient());

        mWebView.setOnLongClickListener(this);
        //TextView tv = new TextView(this);
        //tv.setText(t + "  : " +tlater);
        //setContentView(tv);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onFocusChange(View v, boolean b) {

        if (b) {// (mWebView.isShown()){
            setDates();
            //Toast.makeText(getBaseContext(),"Hello, forcast!",Toast.LENGTH_LONG).show();
            mWebView.reload();
        }
    }

    private void setDates() {
        now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        t = formatter.format(now.getTime());

        Calendar working;
        working = (Calendar) now.clone();
        working.add(Calendar.DAY_OF_YEAR, +3);
        tlater = formatter.format(working.getTime());

    }

    public boolean onLongClick(View v) {

        Toast.makeText(this.getBaseContext(), "Sidan Laddas nu om!", Toast.LENGTH_LONG).show();
        mWebView.reload();

        return true;

    }
}
