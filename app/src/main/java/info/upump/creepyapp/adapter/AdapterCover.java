package info.upump.creepyapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.List;
import java.util.zip.Inflater;

import info.upump.creepyapp.MainActivity;
import info.upump.creepyapp.R;
import info.upump.creepyapp.model.Cover;

/**
 * Created by explo on 30.01.2018.
 */

public class AdapterCover extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Cover> list;

    public AdapterCover(List<Cover> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == 1) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.native_ad_layout, parent, false);
            return new NativeAdViewHolder(v);

        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cover_item_layoyt, parent, false);

            return new CoverHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            return;
        } else ((CoverHolder) holder).bind(list.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 0 && position % 10 == 0) {
            return 1;
        } else return 2;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
