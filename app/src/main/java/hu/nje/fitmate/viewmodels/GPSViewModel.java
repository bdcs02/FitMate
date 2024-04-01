package hu.nje.fitmate.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GPSViewModel extends ViewModel implements LocationListener {

    private final MutableLiveData<Float> speed = new MutableLiveData<>();

    private final MutableLiveData<Double> latitude = new MutableLiveData<>();
    private final MutableLiveData<Double> longitude = new MutableLiveData<>();

    private final MutableLiveData<Integer> permission = new MutableLiveData<>();

    public MutableLiveData<Float> getSpeed() {
        return speed;
    }

    public MutableLiveData<Double> getLatitude() {
        return latitude;
    }

    public MutableLiveData<Double> getLongitude() {
        return longitude;
    }

    public MutableLiveData<Integer> getPermission() {
        return permission;
    }

    public void StartGPS(Context context, Activity activity)
    {
        speed.setValue(0f);
        latitude.setValue(0d);
        longitude.setValue(0d);
        permission.setValue(0);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        } else {
            permission.setValue(1);
            GPS(context);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        speed.setValue(location.getSpeed());
        latitude.setValue(location.getLatitude());
        longitude.setValue(location.getLongitude());
    }

    public void GPS(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (lm != null) {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        Toast.makeText(context,"Waiting for GPS connection!", Toast.LENGTH_SHORT).show();
    }

}
