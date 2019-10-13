package com.example.android.songdetailstart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.songdetailstart.content.SongUtils;

import java.util.List;

/**
 * The ReyclerView for the song list.
 */
class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter
        <SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final List<SongUtils.Song> values;
    private final boolean twoPane;
    private final AppCompatActivity context;

    SimpleItemRecyclerViewAdapter(
            AppCompatActivity ctx,
            List<SongUtils.Song> items,
            boolean twoPane
    ) {
        values = items;
        this.twoPane = twoPane;
        context = ctx;
    }

    /**
     * This method inflates the layout for the song list.
     *
     * @param parent   ViewGroup into which the new view will be added.
     * @param viewType The view type of the new View.
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType
    ) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.song_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            final ViewHolder holder,
            int position
    ) {
        holder.item = values.get(position);
        holder.idView.setText(String.valueOf(position));
        holder.contentView.setText(values.get(position).song_title);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedSong = holder.getAdapterPosition();
                SongDetailFragment fragment = SongDetailFragment.newInstance(selectedSong);
                FragmentTransaction transaction = context
                        .getSupportFragmentManager()
                        .beginTransaction();
                if (twoPane) {
                    transaction.replace(R.id.song_detail_container, fragment);
                } else {
                    transaction.replace(R.id.frameLayout, fragment);
                }
                transaction
                        .addToBackStack("song_detail")
                        .commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        return values.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final TextView idView;
        final TextView contentView;
        SongUtils.Song item;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            idView = view.findViewById(R.id.id);
            contentView = view.findViewById(R.id.content);
        }
    }
}


