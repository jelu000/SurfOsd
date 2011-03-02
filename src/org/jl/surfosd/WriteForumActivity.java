/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jl.surfosd;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.io.*;
import java.net.*;
//import android.

/**
 *
 * @author jens
 */
public class WriteForumActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here

        setContentView(R.layout.mywriteform);

        final TextView textview_name = (TextView) findViewById(R.id.edit_name);
        final TextView textview_mess = (TextView) findViewById(R.id.edit_mess);

        final Button button = (Button) findViewById(R.id.ok);
         button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 // Perform action on click
                 String t_s = "";

                 try {
                     t_s =getData("http://www.salongnobless.se/vader/");
                 }
                 catch (Exception ex){
                     
                 }
                 
                 
                 textview_mess.setText(t_s);
             }
         });

         final Button button_cancel = (Button) findViewById(R.id.cancel);
         button_cancel.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 // Perform action on click
                 System.out.println("click");
             }
         });


    }

    /**Skickar Post och submit request till en Server
     *
     * @param submitURL-adressen
     * @param postdata-postdata om postanvänds
     * @return
     */
    public String getData(String submitURL) throws Exception {
        StringBuffer answer = new StringBuffer();
        URL url = new URL(submitURL);
        URLConnection connection;

        //http://www.salongnobless.se/index.html   action=register&name=somename&email
        //URL url = new URL("http://localhost/jens_www/jlbokningservice/boknings_sever.php?funkcall=checkconn&b_id=test");

        try {
            connection = url.openConnection();
            // connection.setDoOutput(true);
            connection.connect();
            System.out.println("Connecting 2");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine);
                answer.append(inputLine);

            }
            System.out.println("answer=" + answer.toString());
            in.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Is not connected at all...");
            throw e;
        }

        // System.out.println(answer.toString());
        return answer.toString();

    }
}
