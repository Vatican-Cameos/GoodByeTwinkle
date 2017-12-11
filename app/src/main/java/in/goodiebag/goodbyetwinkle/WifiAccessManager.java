package in.goodiebag.goodbyetwinkle;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

/**
 * Created by pavan on 11/12/17.
 */

public class WifiAccessManager {

    private static  String SSID = "Didn't Work";

    public static boolean setWifiApState(Context context, boolean enabled, String SSIDPW) {
        SSID = SSIDPW;
        //config = Preconditions.checkNotNull(config);
        try {
            WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (enabled) {
                mWifiManager.setWifiEnabled(false);
            }
            WifiConfiguration conf = getWifiApConfiguration();
            mWifiManager.addNetwork(conf);

            return (Boolean) mWifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class).invoke(mWifiManager, conf, enabled);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static WifiConfiguration getWifiApConfiguration() {
        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = SSID;
        conf.allowedKeyManagement.set(Integer.parseInt("12345678"));
        return conf;
    }
}
