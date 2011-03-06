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
import android.widget.Toast;
import android.widget.TabHost;
import android.app.AlertDialog;
import android.content.DialogInterface;


import java.io.*;
import java.net.*;
//import android.

/**
 *
 * @author jens lundeqvist
 */
public class WriteForumActivity extends Activity implements DialogInterface {

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
                    t_s = postData("passw", "hemlig", "name", "Kalle", "mess", "Är i Göviken och surfar");

                    if (t_s.equals("OK")) {//If it went ok
                        textview_mess.setText("");
                        textview_name.setText("");
                        Toast.makeText(getBaseContext(), "Lyckades med inlägget på SurfOsd " + t_s, Toast.LENGTH_LONG).show();

                    } else {//if the answer was not ok
                       
                        AlertDialog dialog = new AlertDialog.Builder(WriteForumActivity.this).setTitle("FEL!").setMessage("Inlägg i forum misslyckades!").create();
                        dialog.setButton("OK", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                //here you can add functions
                            }
                        });

                        dialog.show();
                    }


                } catch (Exception ex) {
                    textview_mess.setText(ex.toString());
                }

            }
        });

        final Button button_cancel = (Button) findViewById(R.id.cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Perform action on click
                textview_name.setText("");
                textview_mess.setText("");

            }
        });


    }

    /** PostData() Skickar Post och submit request till en Server
     *
     * @param t_key_1 = passw
     * @param t_value1 = ThePasswordValue
     * @param t_key_2 = name
     * @param t_value2 = TheNameValue
     * @param t_key_3 = mess
     * @param t_value3 = TheMessageValue
     * @return
     */
    public String postData(String t_key1, String t_value1, String t_key2, String t_value2, String t_key3, String t_value3) {

        StringBuffer answer = new StringBuffer();

        try {
            // Construct data
            String data = URLEncoder.encode(t_key1, "UTF-8") + "=" + URLEncoder.encode(t_value1, "UTF-8");
            data += "&" + URLEncoder.encode(t_key2, "UTF-8") + "=" + URLEncoder.encode(t_value2, "UTF-8");
            data += "&" + URLEncoder.encode(t_key3, "UTF-8") + "=" + URLEncoder.encode(t_value3, "UTF-8");

            // Send data
            URL url = new URL("http://192.168.1.7:80/SurfOsdVaderAndroidApp/TestWriteForum.php");//http://hostname:80/cgi
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";

            while ((line = rd.readLine()) != null) {
                // Process line...
                answer.append(line);


            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Kunde inte skicka meddelandet till SurfOsd forum!", Toast.LENGTH_LONG).show();
        }
        return answer.toString();
    }

    @Override
    public void dismiss() {
    }

    @Override
    public void cancel() {
    }
}
