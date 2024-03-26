package hu.nje.fitmate.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.nje.fitmate.MainViewModel;
import hu.nje.fitmate.R;


public class TimerFragment extends Fragment {

    MainViewModel mainViewModel;

    TextView timeTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        // Inflate the layout for this fragment
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        timeTextView = view.findViewById(R.id.timeTextView);
        timeTextView.setText(mainViewModel.getExerciseTimeMinute().getValue().toString());

        return view;
    }


}