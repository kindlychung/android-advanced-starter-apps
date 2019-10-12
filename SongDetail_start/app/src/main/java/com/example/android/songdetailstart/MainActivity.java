/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.songdetailstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.songdetailstart.content.SongUtils;

import java.util.List;

/**
 * An activity representing a list of song titles (items). When one is
 * touched, an intent starts {@link SongDetailActivity} representing
 * song details.
 */
public class MainActivity extends AppCompatActivity {
    private boolean twoPane = false;

    /**
     * Sets up a song list as a RecyclerView.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        if (findViewById(R.id.song_detail_container) != null) {
            twoPane = true;
        }

        // Set the toolbar as the app bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Get the song list as a RecyclerView.
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.song_list);
        recyclerView.setAdapter
                (new SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS));
    }

    /**
     * The ReyclerView for the song list.
     */
    class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter
            <SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<SongUtils.Song> values;

        SimpleItemRecyclerViewAdapter(List<SongUtils.Song> items) {
            values = items;
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
                    if (twoPane) {
                        int selectedSong = holder.getAdapterPosition();
                        SongDetailFragment fragment = SongDetailFragment.newInstance(selectedSong);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.song_detail_container, fragment)
                                .addToBackStack("song_detail")
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context,
                                SongDetailActivity.class);
                        intent.putExtra(SongUtils.SONG_ID_KEY,
                                holder.getAdapterPosition());
                        context.startActivity(intent);
                    }
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
}
