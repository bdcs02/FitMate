package hu.nje.fitmate;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;


@Database(entities = {ExcerciseData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExcerciseDataDao allExcerciseDataDao();
}


