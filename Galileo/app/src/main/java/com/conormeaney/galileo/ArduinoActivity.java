package com.conormeaney.galileo;
/**
 * Created by CONOR on 13/04/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ArduinoActivity extends Activity {

    Button off;
	Button on;
	String server;
	InetAddress ip;
    DatagramSocket data;
    DatagramPacket give,receive;
    private Boolean state()  {

		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);//To see the state in which the router is in
		NetworkInfo ni = cm.getActiveNetworkInfo();
  if(ni != null && ni.isConnected())
    		        return true;

    		    return false;
    		}
  public void motor(String s) throws Exception
    {
    	byte[] b=(s.getBytes());
    	if(state())
    	{
        server = new String("192.168.1.13");
        ip = InetAddress.getByName(server);
		data = new DatagramSocket();
        try{
        	give =  new DatagramPacket(b,b.length, ip, 8081);
        }catch(Exception e){
        }
        data.send(give);
        data.setSoTimeout(10000);
        data.receive(receive);
        InetAddress returnIPAddress = receive.getAddress();
        Toast.makeText(getApplicationContext(), "The galileo is:" + returnIPAddress, Toast.LENGTH_LONG).show();//this will display a toast showing what state the
        data.close();
    	}
  else
    	{
    		Toast.makeText(getApplicationContext(), "connect to a network", Toast.LENGTH_LONG).show();
    	}
    }
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.motor);

  if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

        on=(Button)findViewById(R.id.on);
        off=(Button)findViewById(R.id.off);


        on.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					motor("1");
					Toast.makeText(getApplicationContext(), "OFF", Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					System.out.println("Error::"+e);
				}
			}
		}
		);

        off.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				try {

					motor("2");
					Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_LONG).show();

				} catch (Exception e) {
					System.out.println("Error::"+e);
				}
			}});

    }
}