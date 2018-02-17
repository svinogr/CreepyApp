package info.upump.creepyapp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import info.upump.creepyapp.db.CoverDao;
import info.upump.creepyapp.db.IData;
import info.upump.creepyapp.model.Cover;

/**
 * Created by explo on 08.02.2018.
 */

public class LoaderFavoriteCover extends AsyncTaskLoader<List<Cover>> {

    public LoaderFavoriteCover(Context context) {
        super(context);
    }

    @Override
    public List<Cover> loadInBackground() {
        IData<Cover> coverDao = new CoverDao(getContext());
        return coverDao.getAllFavorite();
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();

    }

    @Override
    public void deliverResult(List<Cover> data) {
        super.deliverResult(data);
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }
}
