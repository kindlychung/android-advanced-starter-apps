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

package com.example.android.fragmentexample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener{

    Button button;
    FrameLayout container;
    boolean showingQuestions = false;
    private final String SHOWING_QUESTIONS = "showingQuestions";

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(SHOWING_QUESTIONS, showingQuestions);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.fragment_container);
        button = findViewById(R.id.button);

        if (savedInstanceState != null) {
            showingQuestions =
                    savedInstanceState.getBoolean(SHOWING_QUESTIONS);
            toggleQuestions(showingQuestions);
            setButtonText();
        }

        button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                toggleQuestions(!showingQuestions);
                showingQuestions = !showingQuestions;
                setButtonText();
            }
        });
    }

    private void setButtonText() {
        if (showingQuestions) button.setText(R.string.close);
        else button.setText(R.string.open);
    }

    private void toggleQuestions(boolean on) {
        FragmentManager manager = getSupportFragmentManager();
        if (on) {
            manager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.fragment_container, new SimpleFragment())
                    .addToBackStack("show_questions")
                    .commit();
        } else {
            manager
                    .beginTransaction()
                    .remove(
                            manager.findFragmentById(R.id.fragment_container)
                    )
                    .addToBackStack("hide_questions")
                    .commit();
        }
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        Toast.makeText(this, "Choice is " + choice, Toast.LENGTH_SHORT).show();
    }
}
