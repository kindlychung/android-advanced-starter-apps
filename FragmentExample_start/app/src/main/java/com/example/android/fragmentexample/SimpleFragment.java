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
    private final static int YES = 0;
    private final static int NO = 1;
    private final static int NONE = -1;
    private int radioButtonChoice = NONE;
    private OnFragmentInteractionListener listener;

    public SimpleFragment() {
        // Required empty public constructor
    }
    private final static String CHOICE = "choice";
    public SimpleFragment newInstance(int choice) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(CHOICE, choice);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener) {
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

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(
                    RatingBar ratingBar,
                    float v,
                    boolean b
            ) {
                Toast.makeText(getContext(), "Rating: " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
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
                            case 0: // User chose "Yes."
                                textView.setText(R.string.yes_message);
                                radioButtonChoice = YES;
                                listener.onRadioButtonChoice(YES);
                                break;
                            case 1: // User chose "No."
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
