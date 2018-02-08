package info.upump.creepyapp;

import android.os.Bundle;
import android.support.v4.content.Loader;

import java.util.List;

import info.upump.creepyapp.loader.LoaderFavoriteCover;
import info.upump.creepyapp.model.Cover;

/**
 * Created by explo on 08.02.2018.
 */

public class FavoriteCoverFragment extends AbstractCoverFragment {
    @Override
    void setTitleAndImg() {
        System.out.println("favorite");
        int imgIdent  = getResources().getIdentifier("history", "drawable", getContext().getPackageName());
        iControllerfragment.setTitle("Избранное", imgIdent);
    }

    public static FavoriteCoverFragment newInstance(){
        return new FavoriteCoverFragment();
    }

    @Override
    public Loader<List<Cover>> onCreateLoader(int id, Bundle args) {
        return new LoaderFavoriteCover(getContext());
    }
}
