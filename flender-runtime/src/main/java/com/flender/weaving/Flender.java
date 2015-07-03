package com.flender.weaving;

/**
 * Created by Joel on 02/07/2015.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by Joel on 30/06/2015.
 */
public class Flender {
    private static WeakReference<Context> contextWeakReference;


    public static void init(Context context) {
        contextWeakReference = new WeakReference<>(context);
    }

    /**
     * Get the network info
     *
     * @return
     */
    protected static NetworkInfo getNetworkInfo() {
        if (contextWeakReference == null) {
            throw new NullPointerException("Context not set.");
        }
        ConnectivityManager cm = (ConnectivityManager) contextWeakReference.get().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * Check if there is any connectivity
     *
     * @return
     */
    protected static boolean isConnected() {
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected());
    }

    /**
     * Check if there is any connectivity to a Wifi network
     *
     * @return
     */
    protected static boolean isConnectedWifi() {
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * Check if there is any connectivity to a mobile network
     *
     * @return
     */
    protected static boolean isConnectedMobile() {
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    protected static void Toast(String text) {
        Toast.makeText(contextWeakReference.get(), text, Toast.LENGTH_LONG).show();
    }


}
