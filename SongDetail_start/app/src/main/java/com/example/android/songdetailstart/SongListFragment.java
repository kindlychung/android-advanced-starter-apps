package com.example.android.songdetailstart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.songdetailstart.content.SongUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SongListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongListFragment extends Fragment {

    private boolean twoPane = false;
    private RecyclerView recyclerView;

    public SongListFragment() {
        // Required empty public constructor
    }


    public static SongListFragment newInstance(
            int selectedSong
    ) {
        SongListFragment fragment = new SongListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View rootView = inflater.inflate(R.layout.song_list, container, false);
        twoPane = (rootView.findViewById(R.id.song_detail_container) != null);
        recyclerView = rootView.findViewById(R.id.song_list);
        recyclerView.setAdapter
                (new SimpleItemRecyclerViewAdapter((AppCompatActivity) getContext(), SongUtils.SONG_ITEMS, twoPane));
        return rootView;
    }

    public boolean isTwoPane() {
        return twoPane;
    }
}
