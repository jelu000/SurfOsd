/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jl.surfosd;

import android.app.*;
import android.os.Bundle;
import android.widget.*;
import android.content.*;
import android.content.res.Resources;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

/**
 *
 * @author jens lundeqvist
 */
public class SurfOSD extends TabActivity implements OnClickListener, OnTabChangeListener {

    TabHost tabHost;  // The activity TabHost
    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
    Intent intent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // ToDo add your GUI initialization code here
        Resources res = getResources(); // Resource object to get Drawables
        tabHost = getTabHost();  // The activity TabHost
        //TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        //Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, ViewWindActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("vind").setIndicator("Vind",
                res.getDrawable(R.drawable.ic_tab_wind)).setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs


        intent = new Intent().setClass(this, ReadForumActivity.class);
        spec = tabHost.newTabSpec("forum").setIndicator("Forum",
                res.getDrawable(R.drawable.ic_tab_forum)).setContent(intent);
        tabHost.addTab(spec);


        intent = new Intent().setClass(this, WriteForumActivity.class);
        spec = tabHost.newTabSpec("skriv").setIndicator("Skriv",
                res.getDrawable(R.drawable.ic_tab_write)).setContent(intent);
        tabHost.addTab(spec);



        intent = new Intent().setClass(this, ForcastActivity.class);
        spec = tabHost.newTabSpec("prognos").setIndicator("Prognos",
                res.getDrawable(R.drawable.ic_tab_forcast)).setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        Log.i("MyActivity", "MyClass.getView() — get item number ");

        tabHost.setOnClickListener(this);


    }

    // Implement the OnClickListener callback
    public void onClick(View v) {
        // do something when the button is clicked
        Toast.makeText(getBaseContext(),
                "click!",
                Toast.LENGTH_LONG).show();



    }

    @Override
    public void onTabChanged(String arg0) {
        //Log.i("******SAHARA CLICK TAB NUMBER", "------" + TabHost.getCurrentTab());
        //Button myButton = (Button) findViewById(R.id.my_button);

        //OBS dettafunkar men används ej, har kommenterat bort lysnaren i konstruktor
        View t_view = tabHost.getCurrentView();
        WebView mWebView = (WebView) t_view.findViewById(R.id.webview);
        mWebView.reload();

    }
}
