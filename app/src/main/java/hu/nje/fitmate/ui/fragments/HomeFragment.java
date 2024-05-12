package hu.nje.fitmate.ui.fragments;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import org.json.JSONObject;
import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.R;
import hu.nje.fitmate.database.AppDatabase;
import hu.nje.fitmate.database.models.Goal;
import hu.nje.fitmate.database.models.Session;
import hu.nje.fitmate.viewmodels.GPSViewModel;
import hu.nje.fitmate.viewmodels.GoalViewModel;


public class HomeFragment extends Fragment {

    GPSViewModel gpsViewModel;
    TextView data;
    String url;
    ImageView idoicon;

    Button start;

    TextView dailygoal;
    TextView prev_calories;
    TextView prev_time;

    //GoalViewModel goalViewModel;

    Button previous;
    TextView forecast;
    GoalViewModel goalViewModel;
     //Goal viewgoal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //goalViewModel = new ViewModelProvider(getActivity()).get(GoalViewModel.class);
        //viewgoal = goalViewModel.getDuration.getValue();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        forecast = view.findViewById(R.id.Tv_Weather);
        dailygoal = view.findViewById(R.id.Tv_daily);
        prev_calories = view.findViewById(R.id.Tv_CalBurn);
        prev_time = view.findViewById(R.id.Tv_Tm);

        SetText();

        gpsViewModel = new ViewModelProvider(this).get(GPSViewModel.class);
        goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);
        data = view.findViewById(R.id.Tv_Temp);
        idoicon = view.findViewById(R.id.Tv_WeatherImg);

        start = view.findViewById(R.id.bt_StartEx);
        previous = view.findViewById(R.id.bt_AllPrevEx);
        if(getAppdatabase() != null) {
            if(getAppdatabase().goalDao().getAllGoals() != null) {
                dailygoal.setText("Daily Goal: " + getAppdatabase().goalDao().getAllGoals().get( getAppdatabase().goalDao().getAllGoals().size() - 1).getDuration() + " min");
            }
            else {
                dailygoal.setText("Daily Goal: ");
            }
        }

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

        start.setOnClickListener(v -> {
            getNavController().navigate(R.id.timerSettingsFragment);
        });


        previous.setOnClickListener(v -> {
            getNavController().navigate(R.id.historyFragment);
        });

        return view;
    }


    private void SetText() {
        if(getAppdatabase() != null)
        {

            Session session = getAppdatabase().sessionDao().getAllSessions().get(getAppdatabase().sessionDao().getAllSessions().size() - 1);
            if(session != null) {
                prev_calories.setText("Calories burnt : " + String.format("%.2f",session.getBurnedCalories()) + " kal");
                prev_time.setText("Time: " + session.getStartTime() + " - " + session.getEndTime() );
            }
        }
    }

    public void IdoJarasLekerdez(String lon,String lat){
        // URL inicializálása
        url = "http://api.weatherapi.com/v1/current.json?key=b3c62761b3034d35bdf142653242403&q="+lon+","+lat+"&aqi=yes";

        // Kérés inicializálása
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    // Válaszfeldolgozás
                    try {
                        JSONObject datatime=response.getJSONObject("current");
                        double homerseklet=datatime.getDouble("temp_c");
                        data.setText("Tempeture: "+homerseklet+" °C");
                        JSONObject felhose=datatime.getJSONObject("condition");
                        String fel=felhose.getString("text");
                        forecast.setText("Todays weather: "+fel);

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

    private NavController getNavController() {
        return ((MainActivity)getActivity()).getNavController();
    }

    private AppDatabase getAppdatabase() { return ((MainActivity)getActivity()).getAppDatabase();}
}