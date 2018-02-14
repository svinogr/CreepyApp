package info.upump.creepyapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import info.upump.creepyapp.R;

/**
 * Created by explo on 13.02.2018.
 */

public class NativeAdViewHolder extends RecyclerView.ViewHolder {
    private NativeExpressAdView mNativeAd;
    public NativeAdViewHolder(View itemView) {
        super(itemView);
        mNativeAd = itemView.findViewById(R.id.nativeAd);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        //You can add the following code if you are testing in an emulator
            /*AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();*/
        mNativeAd.loadAd(adRequest);
    }

}
