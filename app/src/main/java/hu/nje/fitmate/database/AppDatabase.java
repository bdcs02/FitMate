package hu.nje.fitmate.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import hu.nje.fitmate.database.daos.CategoryDao;
import hu.nje.fitmate.database.models.Category;
import hu.nje.fitmate.database.models.Goal;
import hu.nje.fitmate.database.models.Session;


@Database(entities = {Category.class, Goal.class, Session.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
}


