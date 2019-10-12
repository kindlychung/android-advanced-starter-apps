package com.example.android.fragmentexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class SimpleFragment extends Fragment {
    public final static int YES = 0;
    public final static int NO = 1;
    public final static int NONE = -1;
    private int radioButtonChoice = NONE;
    private OnFragmentInteractionListener listener;
    public final static String CHOICE_FIELD = "choice";

    public SimpleFragment() {
        // Required empty public constructor
    }

    public static SimpleFragment newInstance(int choice) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CHOICE_FIELD, choice);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException("Context does not implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        final View rootView =
                inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        final RatingBar ratingBar = rootView.findViewById(R.id.song_rating);

        if (getArguments().containsKey(CHOICE_FIELD)) {
            radioButtonChoice = getArguments().getInt(CHOICE_FIELD);
            if (radioButtonChoice != NONE) {
                radioGroup.check(
                        radioGroup
                                .getChildAt(radioButtonChoice)
                                .getId()
                );
            }
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(
                    RatingBar ratingBar,
                    float v,
                    boolean b
            ) {
                Toast
                        .makeText(getContext(), "Rating: " + ratingBar.getRating(), Toast.LENGTH_SHORT)
                        .show();
            }
        });

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            RadioGroup group,
                            int checkedId
                    ) {
                        View radioButton = radioGroup.findViewById(checkedId);
                        int index = radioGroup.indexOfChild(radioButton);
                        TextView textView =
                                rootView.findViewById(R.id.fragment_header);
                        switch (index) {
                            case YES: // User chose "Yes."
                                textView.setText(R.string.yes_message);
                                radioButtonChoice = YES;
                                listener.onRadioButtonChoice(YES);
                                break;
                            case NO: // User chose "No."
                                textView.setText(R.string.no_message);
                                radioButtonChoice = NO;
                                listener.onRadioButtonChoice(NO);
                                break;
                            default: // No choice made.
                                radioButtonChoice = NONE;
                                listener.onRadioButtonChoice(NONE);
                                break;
                        }
                    }
                });
        return rootView;
    }

    interface OnFragmentInteractionListener {
        void onRadioButtonChoice(int choice);
    }
}
