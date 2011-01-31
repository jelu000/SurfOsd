/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jl.surfosd;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.view.View.OnLongClickListener;
import android.view.View;
import android.widget.Toast;
//import android.app.AlertDialog;
//import android.app.AlertDialog.Builder;

/**
 *
 * @author jens
 */
public class SurfOsdWebViewClient extends WebViewClient implements OnLongClickListener  {

    private WebView globalview;

    /** Called when the activity is first created. */
     @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        globalview = view;
        view.loadUrl(url);
        globalview.setOnLongClickListener(this);
        return true;
    }

    public boolean onLongClick(View  v) {

            //Toast.makeText(getBaseContext(),"Sidan Laddas nu om!",Toast.LENGTH_LONG).show();
       // AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        //globalview.reload();

            return true;

    }


}
