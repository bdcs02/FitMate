package hu.nje.fitmate.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import hu.nje.fitmate.database.models.Session;

@Dao
public interface SessionDao {

    @Insert
    public void insertSession(Session session);

    @Query("SELECT * FROM Session where sessionID = :sessionID")
    public Session getSessionById(int sessionID);

    @Query("SELECT * FROM Session")
    public List<Session> getAllSessions();
}
