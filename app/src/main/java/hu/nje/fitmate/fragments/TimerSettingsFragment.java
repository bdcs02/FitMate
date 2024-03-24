package hu.nje.fitmate.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.R;


public class TimerSettingsFragment extends Fragment {

    int exerciseTimeSecond;
    int exerciseTimeMinute;

    int restTimeSecond;
    int restTimeMinute;

    Button backButton;
    Button startButton;

    Button exerciseTimeButton;
    Button restTimeButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer_settings, container, false);

        backButton = view.findViewById(R.id.backButton);
        startButton = view.findViewById(R.id.startButton);
        exerciseTimeButton = view.findViewById(R.id.ExerciseTimeButton);
        restTimeButton = view.findViewById(R.id.restTimeButton);

        backButton.setOnClickListener(v -> {
            getNavController().navigate(R.id.homeFragment);
        });

        startButton.setOnClickListener(v -> {
            getNavController().navigate(R.id.exerciseFragment);
        });

        exerciseTimeButton.setOnClickListener(v -> {
            TimePicker(true);
        });

        restTimeButton.setOnClickListener(v -> {
            TimePicker(false);
        });
        return view;
    }

    private NavController getNavController() {
        return ((MainActivity)getActivity()).getNavController();
    }

    public void TimePicker(boolean exerciseSelect)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int minute, int second) {
                if(exerciseSelect)
                {
                    exerciseTimeMinute = minute;
                    exerciseTimeSecond = second;
                    exerciseTimeButton.setText(exerciseTimeMinute + ":" + ((exerciseTimeSecond > 9) ? exerciseTimeSecond: "0" + exerciseTimeSecond ));
                }
                else
                {
                    restTimeMinute = minute;
                    restTimeSecond = second;
                    restTimeButton.setText(restTimeMinute + ":" + ((restTimeSecond > 9) ? restTimeSecond: "0" + restTimeSecond ));
                }
            }
        };

        TimePickerDialog timePickerDialog;
        if(exerciseSelect)
        {
            timePickerDialog = new TimePickerDialog(getActivity(),onTimeSetListener,exerciseTimeMinute,exerciseTimeSecond,true);
        }
        else
        {
            timePickerDialog = new TimePickerDialog(getActivity(),onTimeSetListener,restTimeMinute,restTimeSecond,true);
        }
        timePickerDialog.show();
    }
}