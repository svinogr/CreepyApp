package info.upump.creepyapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.zip.Inflater;

import info.upump.creepyapp.R;
import info.upump.creepyapp.model.Cover;

/**
 * Created by explo on 30.01.2018.
 */

public class AdapterCover extends RecyclerView.Adapter<CoverHolder> {
    private List<Cover> list;

    public AdapterCover(List<Cover> list) {
        this.list = list;
    }

    @Override
    public CoverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.cover_item_layoyt, parent, false);

        return new CoverHolder(inflate);
    }

    @Override
    public void onBindViewHolder(CoverHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
