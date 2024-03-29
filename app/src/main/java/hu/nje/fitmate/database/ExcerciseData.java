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

    @Override
    public String toString() {
        return "ExcerciseData{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", distance=" + distance +
                ", maxSpeed=" + maxSpeed +
                ", caloriesBurnt=" + caloriesBurnt +
                '}';
    }
}
