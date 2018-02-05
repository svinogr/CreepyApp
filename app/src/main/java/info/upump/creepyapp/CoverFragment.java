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

public class CoverFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Cover>>, ISwipeController {
    private List<Cover> listCover = new ArrayList<>();
    private AdapterCover adapterCover;
    private IControllerfragment iControllerfragment;

    public CoverFragment() {
        // Required empty public constructor
    }

    public static CoverFragment newInstance() {
        CoverFragment fragment = new CoverFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_cover, container, false);
        RecyclerView recyclerView = inflate.findViewById(R.id.cover_fragment_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapterCover = new AdapterCover(listCover);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterCover);

        ItemTouchHelper.Callback itemTouchHelperCallback = new SwipeCallback(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.INVISIBLE);
        iControllerfragment.setTitle("Истории", null);

        return inflate;
    }

    @Override
    public Loader<List<Cover>> onCreateLoader(int id, Bundle args) {
        return new LoaderCover(getContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Cover>> loader, List<Cover> data) {
        listCover.clear();
        listCover.addAll(data);
        adapterCover.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(Loader<List<Cover>> loader) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getLoaderManager().initLoader(0, null, this);
        iControllerfragment = (IControllerfragment) context;
    }


    @Override
    public void favorite(int positionItem) {
        Cover cover = listCover.get(positionItem);
        cover.setFavorite(!cover.isFavorite());
        CoverDao coverDao = new CoverDao(getContext());
        if (!coverDao.update(cover)) {
            cover.setRead(!cover.isFavorite());
        }
        adapterCover.notifyItemChanged(positionItem);


    }

    @Override
    public void read(int positionItem) {
        Cover cover = listCover.get(positionItem);
        cover.setRead(!cover.isRead());
        CoverDao coverDao = new CoverDao(getContext());
        if (!coverDao.update(cover)) {
            cover.setRead(!cover.isRead());
        }
        adapterCover.notifyItemChanged(positionItem);

    }


}
