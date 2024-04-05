package hu.nje.fitmate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import hu.nje.fitmate.database.AppDatabase;
import hu.nje.fitmate.viewmodels.GPSViewModel;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppDatabase appDatabase;
    private AppBarConfiguration appBarConfiguration;

    GPSViewModel gpsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gpsViewModel = new ViewModelProvider(this).get(GPSViewModel.class);
        gpsViewModel.StartGPS(this,this);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .build();
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);

        //navController.addOnDestinationChangedListener((controller, destination, arguments) -> Log.d("MainActivity", "Navigated to " + destination));





        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database.db").allowMainThreadQueries().build();
        /*ExcerciseDataDao data = db.allExcerciseDataDao();


        ExcerciseData exercise = new ExcerciseData();

        exercise.id = 2;


        exercise.date = "2024-03-19";
        exercise.time = "12:00";
        exercise.distance = 5.0f;
        exercise.maxSpeed = 10;
        exercise.caloriesBurnt = 200;

        data.insertExcercise(exercise);


        ExcerciseData exerciseData = data.getDataById(1); */


    }

    public NavController getNavController() {
        return navController;
    }
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_home) {
            setTitle("Home");
            navController.navigate(R.id.homeFragment);
        } else if(id == R.id.action_options) {
            setTitle("Options");
            navController.navigate(R.id.timerSettingsFragment);
        } else if(id == R.id.action_history) {
            setTitle("History");
            navController.navigate(R.id.historyFragment);
        }  else if(id == R.id.action_settings) {
            setTitle("Settings");
            navController.navigate(R.id.settingsFragment);
        }
        if(item.getItemId() == R.id.action_timerSettingsFragment) {
            navController.navigate(R.id.timerSettingsFragment);
        }
        return super.onOptionsItemSelected(item);
    }





}