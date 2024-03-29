package hu.nje.fitmate.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ExcerciseDataDao {
    @Insert
    public void insertExcercise(ExcerciseData excercise);


    @Query("SELECT * FROM ExcerciseData where id = :id")
    public ExcerciseData getDataById(int id);


}
