package hu.nje.fitmate.fragments;

import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.MainViewModel;
import hu.nje.fitmate.R;


public class TimerFragment extends Fragment {

    MainViewModel mainViewModel;

    TextView timeTextView;
    TextView statusTextView;

    int exerciseMinute = 5;
    int exerciseSecond = 0;
    int restMinute = 2;
    int restSecond = 0;
    int sets = 5;

    boolean exercise = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        // Inflate the layout for this fragment
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        timeTextView = view.findViewById(R.id.timeTextView);
        statusTextView = view.findViewById(R.id.statusTextView);

        exerciseMinute = mainViewModel.getExerciseTimeMinute().getValue();
        exerciseSecond = mainViewModel.getExerciseTimeSecond().getValue();
        restMinute = mainViewModel.getRestTimeMinute().getValue();
        restSecond = mainViewModel.getRestTimeSecond().getValue();
        sets = mainViewModel.getSets().getValue();

        Exercise();

        return view;
    }

    void SetTimer(int minute, int second)
    {
        final int countdown = minute * 60000 + second * 1000;
        new CountDownTimer(countdown, 1000){
            public void onTick(long millisUntilFinished){
                int minute = (int) (millisUntilFinished / 60000);
                int second = (int) (millisUntilFinished / 1000 % 60);
                timeTextView.setText( minute + ":" + ((second > 9) ? second : "0" + second));
            }
            public  void onFinish(){
                exercise=!exercise;
                sets--;
                Exercise();
            }
        }.start();
    }

    void Exercise()
    {
        if(sets <= 0)
        {
            getNavController().navigate(R.id.homeFragment);
        }

        if(exercise)
        {
            SetTimer(exerciseMinute,exerciseSecond);
            statusTextView.setText("Running");
        }
        else
        {
            SetTimer(restMinute,restSecond);
            statusTextView.setText("Walking");
        }
    }

    private NavController getNavController() {
        return ((MainActivity)getActivity()).getNavController();
    }

}