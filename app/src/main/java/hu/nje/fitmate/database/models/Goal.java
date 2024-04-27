package hu.nje.fitmate.database.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys =
@ForeignKey(entity = Category.class,
        parentColumns = "categoryID",
        childColumns = "categoryID",
        onDelete = ForeignKey.CASCADE))
public class Goal {

    public Goal() {
    }
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int goalID;

    private String goalDesc;

//    private double weight;

    private double duration;

    private double burnedCalories;

    private boolean isAchieved;

    private int categoryID;

    public Goal(String goalDesc,double weight, double duration, double burnedCalories, boolean isAchieved, int categoryID) {
        this.goalDesc = goalDesc;
        this.duration = duration;
//        this.weight = weight;
        this.burnedCalories = burnedCalories;
        this.isAchieved = isAchieved;
        this.categoryID = categoryID;
    }


    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

//    public double getWeight() {
//        return weight;
//    }
//
//    public void setWeight(double weight) {
//        this.weight = weight;
//    }

    public String getGoalDesc() {
        return goalDesc;
    }

    public void setGoalDesc(String goalDesc) {
        this.goalDesc = goalDesc;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(double burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public boolean isAchieved() {
        return isAchieved;
    }

    public void setAchieved(boolean achieved) {
        isAchieved = achieved;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
