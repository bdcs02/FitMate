package hu.nje.fitmate.fragments;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import org.json.JSONObject;
import hu.nje.fitmate.R;
import hu.nje.fitmate.viewmodels.GPSViewModel;


public class HomeFragment extends Fragment {

    GPSViewModel gpsViewModel;
    TextView data;
    String url;
    ImageView idoicon;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        gpsViewModel = new ViewModelProvider(this).get(GPSViewModel.class);

        data = view.findViewById(R.id.Tv_Temp);
        idoicon = view.findViewById(R.id.Tv_WeatherImg);

        gpsViewModel.setContext(getContext());
        //GPS
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1000);

        } else {
            gpsViewModel.Start();
            TextView textView = view.findViewById(R.id.textView3);
            String lat, lon;
            if (gpsViewModel.getLatitude().getValue() != null && gpsViewModel.getLongitude().getValue() != null) {
                lat = String.format("%.2f", gpsViewModel.getLatitude().getValue());
                lon = String.format("%.2f", gpsViewModel.getLongitude().getValue());

            }
            else {
                lat = "19.08";//gps adat
                lon = "47.5";
                //Budapest
            }
            textView.setText("Lat:"+lat+ " Lon:" + lon);
            IdoJarasLekerdez(lon,lat);//idojaras lekerdezes
        }

        return view;
    }

    public void IdoJarasLekerdez(String lon,String lat){
        // URL inicializálása
        url = "http://api.weatherapi.com/v1/current.json?key=b3c62761b3034d35bdf142653242403&q=Budapest&aqi=yes";

        // Kérés inicializálása
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    // Válaszfeldolgozás
                    try {
                        JSONObject datatime=response.getJSONObject("current");
                        double homerseklet=datatime.getDouble("temp_c");
                        data.setText(""+homerseklet);

                        Toast.makeText(getContext(), "Sikeres lekérdezés", Toast.LENGTH_SHORT).show();

                        // Kép letöltése és megjelenítése Glide segítségével

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Sikertelen lekérdezés", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    // Hibakezelés
                    //data.setText(error.toString());
                    error.printStackTrace();
                    data.setText(error.toString());
                });

        // RequestQueue létrehozása és a kérés hozzáadása
        Volley.newRequestQueue(getContext()).add(request);
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