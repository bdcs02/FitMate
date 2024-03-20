package hu.nje.fitmate.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {ExcerciseData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExcerciseDataDao allExcerciseDataDao();
}


