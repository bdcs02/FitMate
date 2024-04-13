package hu.nje.fitmate.fragments;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.nje.fitmate.R;
import hu.nje.fitmate.viewmodels.GPSViewModel;


public class HomeFragment extends Fragment {

    GPSViewModel gpsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        gpsViewModel = new ViewModelProvider(this).get(GPSViewModel.class);

        gpsViewModel.setContext(getContext());
        //GPS
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1000);

        } else {
            gpsViewModel.Start();
            TextView textView = view.findViewById(R.id.textView3);
            textView.setText("Lat:"+gpsViewModel.getLatitude().getValue() + " Lon:" + gpsViewModel.getLongitude().getValue());
        }

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        gpsViewModel.setContext(getContext());
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        }
        else
        {
            gpsViewModel.Start();
        }
    }
}