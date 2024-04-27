package hu.nje.fitmate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import hu.nje.fitmate.database.AppDatabase;
import hu.nje.fitmate.database.models.Category;
import hu.nje.fitmate.database.models.Session;

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

        // insert mock data
        if(appDatabase.categoryDao().getCategorys().isEmpty())
        {
            appDatabase.categoryDao().insertCategory(new Category("Futás","Leírás"));
            appDatabase.categoryDao().insertCategory(new Category("Kocogás","Leírás"));
            appDatabase.categoryDao().insertCategory(new Category("Sprintelés","Leírás"));
            appDatabase.categoryDao().insertCategory(new Category("Gyaloglás","Leírás"));
            appDatabase.categoryDao().insertCategory(new Category("Plank","Leírás"));
        }

        if(appDatabase.sessionDao().getAllSessions().isEmpty()) {
            appDatabase.sessionDao().insertSession(new Session("2024.04.01 11:00", "2024.04.01 12:00", 122.5, "Session description", 1));
            appDatabase.sessionDao().insertSession(new Session("2024.04.01 12:00", "2024.04.01 13:00", 150.9, "Session description", 1));
            appDatabase.sessionDao().insertSession(new Session("2024.04.01 13:00", "2024.04.01 14:00", 210, "Session description", 2));
            appDatabase.sessionDao().insertSession(new Session("2024.04.01 15:00", "2024.04.01 16:00", 172.2, "Session description", 2));
            appDatabase.sessionDao().insertSession(new Session("2024.04.01 17:00", "2024.04.01 18:00", 87.9, "Session description", 3));
            appDatabase.sessionDao().insertSession(new Session("2024.04.01 03:00", "2024.04.01 04:00", 56.1, "Session description", 3));
            appDatabase.sessionDao().insertSession(new Session("2024.04.01 07:00", "2024.04.01 08:00", 98.3, "Session description", 4));
            appDatabase.sessionDao().insertSession(new Session("2024.04.01 09:00", "2024.04.01 10:00", 85.7, "Session description", 4));
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
        } else if(id == R.id.action_history) {
            setTitle("History");
            navController.navigate(R.id.historyFragment);
        }  else if(id == R.id.action_goal) {
            setTitle("Settings");
            navController.navigate(R.id.goalFragment);
        }
        if(item.getItemId() == R.id.action_timerSettingsFragment) {
            navController.navigate(R.id.timerSettingsFragment);
        }
        return super.onOptionsItemSelected(item);
    }
}