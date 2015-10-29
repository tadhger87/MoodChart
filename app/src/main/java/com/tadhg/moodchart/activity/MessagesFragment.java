package com.tadhg.moodchart.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tadhg.moodchart.R;
import com.tadhg.moodchart.database.MoodDAO;

/**
 * Created by Tadhg on 22/09/2015.
 */
public class MessagesFragment extends Fragment {

    TextView db;
    MoodDAO moodDAO;
    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);

        db = (TextView) rootView.findViewById(R.id.label);

        moodDAO.getMoods();
        db.setText((CharSequence) moodDAO.getMoods());
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

