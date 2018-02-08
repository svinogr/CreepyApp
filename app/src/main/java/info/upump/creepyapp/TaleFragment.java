package info.upump.creepyapp;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import info.upump.creepyapp.db.IData;
import info.upump.creepyapp.db.TaleDao;
import info.upump.creepyapp.loader.RandomImg;
import info.upump.creepyapp.model.Author;
import info.upump.creepyapp.model.Cover;
import info.upump.creepyapp.model.Tale;


public class TaleFragment extends Fragment {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String RATE = "rate";
    private static final String FAVORITE = "favorite";
    private static final String READ = "read";
    private static final String ID_AUTHOR = "id Author";
    private static final String IMG = "img";
    private Cover cover;
    private Tale tale;
    private WebView text;
    private TextView title;
    private Toolbar toolbar;
    private IControllerFragment iControllerfragment;

    public TaleFragment() {
        // Required empty public constructor
    }

    public static TaleFragment newInstance(Cover cover) {
        TaleFragment fragment = new TaleFragment();
        Bundle args = new Bundle();
        args.putLong(ID, cover.getId());
        args.putString(TITLE, cover.getTitle());
        args.putInt(RATE, cover.getRate());
        args.putBoolean(FAVORITE, cover.isFavorite());
        args.putBoolean(READ, cover.isRead());
        args.putLong(ID_AUTHOR, cover.getAuthor().getId());
        args.putString(IMG, cover.getImg());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        if (getArguments() != null) {
            cover = new Cover();
            cover.setId(getArguments().getLong(ID));
            cover.setTitle(getArguments().getString(TITLE));
            cover.setRate(getArguments().getInt(RATE));
            cover.setFavorite(getArguments().getBoolean(FAVORITE));
            cover.setRead(getArguments().getBoolean(READ));
            cover.setAuthor(new Author(getArguments().getLong(ID_AUTHOR)));
            cover.setImg(getArguments().getString(IMG));
        }
        IData iData = new TaleDao(getContext());
        this.tale = (Tale) iData.getByParent(cover).get(0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("onCreateView");
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_tale, container, false);

        FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("fab fab");
            }
        });


        System.out.println(cover.getTitle());
        int imgIdent = RandomImg.getRandomIdentForImg(getContext());
        iControllerfragment.setTitle(cover.getTitle(),imgIdent);

        text = inflate.findViewById(R.id.fragment_tale_text);
        text.loadDataWithBaseURL(null, tale.getText(), "text/html", "UTF-8", null);

        return inflate;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iControllerfragment = (IControllerFragment) context;
    }
}
