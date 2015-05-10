package positioning.com.wifipositioning;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.List;



/**
 * Created by Dell Inspiron on 5/10/2015.
 */
public class TestService extends android.app.Service  {




    Thread t;




    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Start", "started");
        WifiManager wifiManager=(WifiManager)this.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> results = wifiManager.getScanResults();
        for (final ScanResult result : results){
            if(result.SSID.contains("mihir")) {
                Log.d("t",result.level+"");
                try {

                    t = new Thread() {
                        public void run() {

                            Message mymessage= new Message();
                            Bundle resBundle = new Bundle();
                            if (result.level < -60)
                                resBundle.putString("status", "Room1");
                            else
                                resBundle.putString("status", "room2");

                            mymessage.setData(resBundle);
                            handler.sendMessage(mymessage);
                        }
                    };
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
            

        }
        t.start();

        return START_STICKY;
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
           Bundle getmessage = msg.getData();
            String putmessage = getmessage.getString("status");
            Toast.makeText(getApplicationContext(), putmessage , Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }




}