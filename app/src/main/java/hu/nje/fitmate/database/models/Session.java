package hu.nje.fitmate.database.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys =
        @ForeignKey(entity = Category.class,
                parentColumns = "categoryID",
                childColumns = "categoryID",
                onDelete = ForeignKey.CASCADE
        )
)
public class Session {

    public Session() {
    }

    public Session(String startTime, String endTime, double burnedCalories, String sessionDesc, int categoryID) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.burnedCalories = burnedCalories;
        this.sessionDesc = sessionDesc;
        this.categoryID = categoryID;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int sessionID;

    private String startTime;
    private String endTime;

    private double burnedCalories;

    private String sessionDesc;

    private int categoryID;



    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(double burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public String getSessionDesc() {
        return sessionDesc;
    }

    public void setSessionDesc(String sessionDesc) {
        this.sessionDesc = sessionDesc;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
