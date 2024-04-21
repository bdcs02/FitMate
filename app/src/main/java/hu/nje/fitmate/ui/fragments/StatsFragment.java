package hu.nje.fitmate.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.R;
import hu.nje.fitmate.viewmodels.TimerToStatsViewModel;


public class StatsFragment extends Fragment {

    private Button saveExitButton;
    private TextView statisticsTextView;
    private TextView timeText;
    private TextView distanceText;
    private TextView maxSpeedText;
    private TextView caloriesText;

    private TimerToStatsViewModel timerToStatsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        timerToStatsViewModel = new ViewModelProvider(getActivity()).get(TimerToStatsViewModel.class);
        saveExitButton = view.findViewById(R.id.saveExitButton);
        statisticsTextView = view.findViewById(R.id.statisticsTextView);
        timeText = view.findViewById(R.id.timeText);
        distanceText = view.findViewById(R.id.distanceText);
        maxSpeedText = view.findViewById(R.id.maxspeedText);
        caloriesText = view.findViewById(R.id.calories);


       timerToStatsViewModel.getDuration().observe(getViewLifecycleOwner(), duration ->
                distanceText.setText("Duration " + String.valueOf(duration) + " minutes"));

        timerToStatsViewModel.getGpsMaximumSpeed().observe(getViewLifecycleOwner(), gps ->
                maxSpeedText.setText("Max Speed: " + String.valueOf(gps)));

        timerToStatsViewModel.getStartTime().observe(getViewLifecycleOwner(), startTime -> {
            String endTime = timerToStatsViewModel.getEndTime().getValue();
            timeText.setText("Time: " + startTime + " " + endTime);
        });
        saveExitButton.setOnClickListener(v -> getNavController().navigate(R.id.historyFragment));
        return view;
    }

    private NavController getNavController() {
        return ((MainActivity)getActivity()).getNavController();
    }

}