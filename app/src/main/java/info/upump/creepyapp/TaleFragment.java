package info.upump.creepyapp;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import info.upump.creepyapp.db.CoverDao;
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
    private FloatingActionButton floatingActionButton;
    private WebView text;
    private TextView title;
    private Toolbar toolbar;
    private IControllerFragment iControllerfragment;
    private static final String START_HTML = "<html><body style=\"text-align:justify; color:";
    private static final String MIDLLE_HTML = " \">";
    private static final String END_HTML = "</body></html>";


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
            System.out.println("onCreate2");
            cover = new Cover();
            cover.setId(getArguments().getLong(ID));
            cover.setTitle(getArguments().getString(TITLE));
            cover.setRate(getArguments().getInt(RATE));
            if (savedInstanceState != null) {
                cover.setFavorite(savedInstanceState.getBoolean(FAVORITE));
            } else cover.setFavorite(getArguments().getBoolean(FAVORITE));
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

        floatingActionButton = getActivity().findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.VISIBLE);
        setFavoriteColorToFab();


        int imgIdent = RandomImg.getRandomIdentForImg(getContext());
        iControllerfragment.setTitle(cover.getTitle(), imgIdent);

        text = inflate.findViewById(R.id.fragment_tale_text);
        text.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorBackWeb));
        text.loadDataWithBaseURL(null, getColoredTextForWebView(), "text/html", "UTF-8", null);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cover.setFavorite(!cover.isFavorite());
                CoverDao coverDao = new CoverDao(getContext());
                if (coverDao.update(cover)) {
                    setFavoriteColorToFab();
                }
            }
        });

        cover.setRead(true);
        CoverDao coverDao = new CoverDao(getContext());
        coverDao.update(cover);

        return inflate;
    }

    private void setFavoriteColorToFab() {
        System.out.println(cover.isFavorite());
        if (cover.isFavorite()) {
            floatingActionButton.setColorFilter(Color.RED);
        } else floatingActionButton.setColorFilter(Color.WHITE);
    }

    @NonNull
    private String getColoredTextForWebView() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(START_HTML);
        stringBuilder.append("#" + Integer.toHexString(ContextCompat.getColor(getContext(), R.color.colorTextWeb) & 0x00ffffff) + MIDLLE_HTML);
        stringBuilder.append(tale.getText());
        stringBuilder.append(END_HTML);
        return stringBuilder.toString();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iControllerfragment = (IControllerFragment) context;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FAVORITE, cover.isFavorite());
    }
}
