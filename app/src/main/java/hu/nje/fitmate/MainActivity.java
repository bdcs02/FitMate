package hu.nje.fitmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app.db").allowMainThreadQueries().build();
        ExcerciseDataDao data = db.allExcerciseDataDao();

        ExcerciseData excercise = new ExcerciseData();
        excercise.id = 1;
        excercise.type = "Futás";
        excercise.date = "2024-03-19";
        excercise.time = "12:00";
        excercise.distance = 5.0f;
        excercise.maxSpeed = 10;
        excercise.caloriesBurnt = 200;

        data.insertExcercise(excercise);


        ExcerciseData excerciseData = data.getDataById(1);

        Button testBtn = findViewById(R.id.testBtn);
        testBtn.setOnClickListener(v -> Toast.makeText(getApplicationContext(),excerciseData.type,
                Toast.LENGTH_SHORT).show());



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}