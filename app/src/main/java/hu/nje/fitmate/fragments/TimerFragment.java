package hu.nje.fitmate.fragments;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.os.CountDownTimer;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.viewmodels.GPSViewModel;
import hu.nje.fitmate.viewmodels.TimerSettingsViewModel;
import hu.nje.fitmate.R;


public class TimerFragment extends Fragment {

    TimerSettingsViewModel timerSettingsViewModel;
    GPSViewModel gpsViewModel;

    TextView timeTextView;
    TextView statusTextView;
    TextView speedTextView;
    View view;

    int exerciseMinute = 5;
    int exerciseSecond = 0;
    int restMinute = 2;
    int restSecond = 0;
    int sets = 5;

    float speed;

    boolean exercise = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timer, container, false);
        // Inflate the layout for this fragment
        timerSettingsViewModel = new ViewModelProvider(getActivity()).get(TimerSettingsViewModel.class);
        gpsViewModel = new ViewModelProvider(getActivity()).get(GPSViewModel.class);

        timeTextView = view.findViewById(R.id.timeTextView);
        statusTextView = view.findViewById(R.id.statusTextView);
        speedTextView = view.findViewById(R.id.speedTextView);

        timerSettingsViewModel.SetValues();

        exerciseMinute = timerSettingsViewModel.getExerciseTimeMinute().getValue();
        exerciseSecond = timerSettingsViewModel.getExerciseTimeSecond().getValue();
        restMinute = timerSettingsViewModel.getRestTimeMinute().getValue();
        restSecond = timerSettingsViewModel.getRestTimeSecond().getValue();
        sets = timerSettingsViewModel.getSets().getValue();

        Exercise();

        if (gpsViewModel.getPermission().getValue() != 1) {
            gpsViewModel.StartGPS(getContext(),getActivity());
        } else {
            gpsViewModel.getSpeed().observe(getActivity(), data ->
                    speedTextView.setText( String.format("%.2f", data) + "m/s" )
            );
        }

        //ANIMATION
        ImageView backgroundCircle = view.findViewById(R.id.backgroundCircle);
        ImageView backgroundCircle2 = view.findViewById(R.id.backgroundCircle2);

        Animation scaleUpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up_animation);
        Animation scaleDownAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_down_animation);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_animation);

        AnimationSet animationUpSet = new AnimationSet(true);
        animationUpSet.addAnimation(scaleUpAnimation);
        animationUpSet.addAnimation(fadeInAnimation);

        AnimationSet animationDownSet = new AnimationSet(true);
        animationDownSet.addAnimation(scaleDownAnimation);
        animationDownSet.addAnimation(fadeInAnimation);


        backgroundCircle.startAnimation(animationUpSet);
        backgroundCircle2.startAnimation(animationDownSet);


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
            view.setBackgroundColor(getResources().getColor(R.color.light_blue));
        }
        else
        {
            SetTimer(restMinute,restSecond);
            statusTextView.setText("Walking");
            view.setBackgroundColor(getResources().getColor(R.color.light_green));
        }
    }

    private NavController getNavController() {
        return ((MainActivity)getActivity()).getNavController();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            gpsViewModel.StartGPS(getContext(),getActivity());
        } else {
            gpsViewModel.getSpeed().observe(getActivity(), data ->
                    speedTextView.setText( String.format("%.2f", data) + "m/s" )
            );
        }
    }
}