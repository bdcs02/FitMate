package hu.nje.fitmate.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.nje.fitmate.database.models.Goal;
import hu.nje.fitmate.database.models.Session;

@Dao
public interface GoalDao {

    @Insert
    public void insertGoal(Goal goal);

    @Query("SELECT * FROM Goal where goalID = :goalID")
    public Goal getGoalById(int goalID);

    @Query("SELECT * FROM Goal")
    public List<Goal> getAllGoals();
}
