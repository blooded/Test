package positioning.com.wifipositioning;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.List;


/**
 * Created by Mihir on 5/9/2015.
 */
public class Service extends android.app.Service implements Runnable {

    List<ScanResult> results;
    WifiManager wifiManager;
    String TM = "";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Start","started");
        wifiManager=(WifiManager)this.getSystemService(Context.WIFI_SERVICE);
        results = wifiManager.getScanResults();
        for (ScanResult result : results){
            while(result.SSID.contains("mihir")&&result.level>-60) {
                Log.d("t",result.level+"");

            }
        }

        return START_STICKY;
    }

    @Override
    public void run() {

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
