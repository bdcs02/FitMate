package hu.nje.fitmate.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExcerciseData {
    @PrimaryKey
    public int id;
    public String type;
    public String date;
    public String time;
    public float distance;
    public int maxSpeed;

    public int caloriesBurnt;
}
