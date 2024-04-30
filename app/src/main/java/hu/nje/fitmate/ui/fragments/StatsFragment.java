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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.R;
import hu.nje.fitmate.database.AppDatabase;
import hu.nje.fitmate.database.models.Goal;
import hu.nje.fitmate.database.models.Session;
import hu.nje.fitmate.viewmodels.GoalViewModel;
import hu.nje.fitmate.viewmodels.TimerSettingsViewModel;
import hu.nje.fitmate.viewmodels.TimerToStatsViewModel;


public class StatsFragment extends Fragment {

    private Button saveExitButton;
    private TextView statisticsTextView;
    private TextView timeText;
    private TextView distanceText;
    private TextView maxSpeedText;
    private TextView caloriesText;

    private TimerToStatsViewModel timerToStatsViewModel;
    private TimerSettingsViewModel timerSettingsViewModel;
    private GoalViewModel goalViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        timerToStatsViewModel = new ViewModelProvider(getActivity()).get(TimerToStatsViewModel.class);
        timerSettingsViewModel = new ViewModelProvider(getActivity()).get(TimerSettingsViewModel.class);
        goalViewModel = new ViewModelProvider(getActivity()).get(GoalViewModel.class);
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

        saveExitButton.setOnClickListener(v -> {
            try {

                String startTime = timerToStatsViewModel.getStartTime().getValue();
                String endTime = timerToStatsViewModel.getEndTime().getValue();
                String categoryDesc = timerSettingsViewModel.getExerciseType().getValue();
                goalViewModel.calculateCalories();
                //double burnedCalories = goalViewModel.getBurnedCalories().getValue();
                Session session = new Session(startTime,endTime,1000,categoryDesc,1);

                getAppdatabase().sessionDao().insertSession(session);
                Toast.makeText(getContext(), "Session saved!", Toast.LENGTH_SHORT).show();

            } catch(Exception e) {
                Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
            }
            getNavController().navigate(R.id.historyFragment);
        });
        return view;
    }

    private NavController getNavController() {
        return ((MainActivity)getActivity()).getNavController();
    }

    private AppDatabase getAppdatabase() {
        return ((MainActivity)getActivity()).getAppDatabase();
    }
}