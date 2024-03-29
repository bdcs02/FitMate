package hu.nje.fitmate.fragments;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.MainViewModel;
import hu.nje.fitmate.R;


public class TimerFragment extends Fragment  implements LocationListener {

    MainViewModel mainViewModel;

    TextView timeTextView;
    TextView statusTextView;
    TextView speedTextView;

    int exerciseMinute = 5;
    int exerciseSecond = 0;
    int restMinute = 2;
    int restSecond = 0;
    int sets = 5;

    float speed;

    boolean exercise = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        // Inflate the layout for this fragment
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        timeTextView = view.findViewById(R.id.timeTextView);
        statusTextView = view.findViewById(R.id.statusTextView);
        speedTextView = view.findViewById(R.id.speedTextView);

        exerciseMinute = mainViewModel.getExerciseTimeMinute().getValue();
        exerciseSecond = mainViewModel.getExerciseTimeSecond().getValue();
        restMinute = mainViewModel.getRestTimeMinute().getValue();
        restSecond = mainViewModel.getRestTimeSecond().getValue();
        sets = mainViewModel.getSets().getValue();

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        } else {
            GPS();
            Exercise();
        }

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

    private void GPS() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (lm != null) {
            if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        Toast.makeText(getContext(),"Waiting for GPS connection!", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onLocationChanged(@NonNull Location location) {

        speed = location.getSpeed();
        speedTextView.setText(String.format("%.2f",speed) + " m/s");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                GPS();
            }

        }

    }

}