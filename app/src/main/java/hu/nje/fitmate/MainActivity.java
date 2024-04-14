package hu.nje.fitmate;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import hu.nje.fitmate.database.AppDatabase;
import hu.nje.fitmate.database.models.Category;
import hu.nje.fitmate.viewmodels.GPSViewModel;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppDatabase appDatabase;
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .build();
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);

        //navController.addOnDestinationChangedListener((controller, destination, arguments) -> Log.d("MainActivity", "Navigated to " + destination));


        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database.db").allowMainThreadQueries().build();

        if(appDatabase.categoryDao().getCategorys().isEmpty())
        {
            appDatabase.categoryDao().insertCategory(new Category("Futás","Leírás"));
            appDatabase.categoryDao().insertCategory(new Category("Kocogás","Leírás"));
            appDatabase.categoryDao().insertCategory(new Category("Sprintelés","Leírás"));
            appDatabase.categoryDao().insertCategory(new Category("Gyaloglás","Leírás"));
            appDatabase.categoryDao().insertCategory(new Category("Plank","Leírás"));
        }

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