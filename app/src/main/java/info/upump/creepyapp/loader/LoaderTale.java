package info.upump.creepyapp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import info.upump.creepyapp.db.IData;
import info.upump.creepyapp.db.TaleDao;
import info.upump.creepyapp.model.Cover;
import info.upump.creepyapp.model.Tale;

/**
 * Created by explo on 03.02.2018.
 */

public class LoaderTale extends AsyncTaskLoader<Tale> {
    private Cover cover;

    public LoaderTale(Context context, Cover cover) {
        super(context);
        this.cover = cover;
    }

    @Override
    public Tale loadInBackground() {
        IData taleDao = new TaleDao(getContext());
        Tale tale = (Tale) taleDao.getByParent(cover);
        return tale;
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
    public void deliverResult(Tale data) {
        super.deliverResult(data);
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }
}
