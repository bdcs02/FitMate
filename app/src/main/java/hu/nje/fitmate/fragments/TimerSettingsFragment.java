package hu.nje.fitmate.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.MainViewModel;
import hu.nje.fitmate.R;


public class TimerSettingsFragment extends Fragment {

    Button backButton;
    Button startButton;

    Button exerciseTimeButton;
    Button restTimeButton;

    MainViewModel mainViewModel;
    int minuteText;
    int secondText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer_settings, container, false);

        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        backButton = view.findViewById(R.id.backButton);
        startButton = view.findViewById(R.id.startButton);
        exerciseTimeButton = view.findViewById(R.id.ExerciseTimeButton);
        restTimeButton = view.findViewById(R.id.restTimeButton);
        EditText setsEditText = view.findViewById(R.id.SetsEditTextNumber);

        backButton.setOnClickListener(v -> {
            getNavController().navigate(R.id.homeFragment);
        });

        startButton.setOnClickListener(v -> {
            if(setsEditText.getText().toString() != "")
            {
                mainViewModel.getSets().setValue(Integer.parseInt(setsEditText.getText().toString())  * 2);
            }
            getNavController().navigate(R.id.action_timerSettingsFragment_to_timerFragment);
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
                    mainViewModel.getExerciseTimeMinute().setValue(minute);
                    mainViewModel.getExerciseTimeSecond().setValue(second);
                    exerciseTimeButton.setText(minute + ":" + ((second > 9) ? second: "0" + second ));
                }
                else
                {
                    mainViewModel.getRestTimeMinute().setValue(minute);
                    mainViewModel.getRestTimeSecond().setValue(second);
                    restTimeButton.setText(minute + ":" + ((second > 9) ? second: "0" + second));
                }
                minuteText = minute;
                secondText = second;
            }
        };

        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(getActivity(),onTimeSetListener,minuteText,secondText,true);
        timePickerDialog.show();
    }
}