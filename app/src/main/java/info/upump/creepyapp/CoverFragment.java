package info.upump.creepyapp;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import info.upump.creepyapp.adapter.AdapterCover;
import info.upump.creepyapp.callback.SwipeCallback;
import info.upump.creepyapp.db.CoverDao;
import info.upump.creepyapp.loader.LoaderCover;
import info.upump.creepyapp.model.Cover;

public class CoverFragment extends AbstractCoverFragment {


    public CoverFragment() {
        // Required empty public constructor
    }

    public static CoverFragment newInstance() {
        CoverFragment fragment = new CoverFragment();
        return fragment;
    }

    @Override
    void setTitleAndImg() {
        System.out.println("history");
        int imgIdent  = getResources().getIdentifier("history", "drawable", getContext().getPackageName());
        iControllerfragment.setTitle(getString(R.string.cover_title_history), imgIdent);
    }

    @Override
    public Loader<List<Cover>> onCreateLoader(int id, Bundle args) {
        return new LoaderCover(getContext());
    }

    @Override
    void notifyFavorite(int positionItem) {
        adapterCover.notifyItemChanged(positionItem);
    }


}
