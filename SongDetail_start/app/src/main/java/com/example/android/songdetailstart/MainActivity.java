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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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
        SongListFragment songListFragment = new SongListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, songListFragment)
                .commit();

    }

}
