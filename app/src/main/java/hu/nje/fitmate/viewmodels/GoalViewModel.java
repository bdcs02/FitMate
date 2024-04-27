package hu.nje.fitmate.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GoalViewModel extends ViewModel {
    private final double METs = 6; // running
    private final MutableLiveData<Double> weight = new MutableLiveData<>();
    private final MutableLiveData<Double> duration = new MutableLiveData<>();
    private final MutableLiveData<Double> burnedCalories = new MutableLiveData<>();

    public MutableLiveData<Double> getWeight() {
        return weight;
    }

    public MutableLiveData<Double> getDuration() {
        return duration;
    }

    public MutableLiveData<Double> getBurnedCalories() {
        return burnedCalories;
    }


    public void calculateCalories() {
        if(duration.getValue() != null && weight.getValue() != null) {
            double durationInHours = duration.getValue() / 60;
            double calories = Math.round(durationInHours * weight.getValue() * METs * 100.0) / 100.0;
            burnedCalories.setValue(calories);
        }
    }

    public void setWeight(double weight) {
        this.weight.setValue(weight);
        calculateCalories();
    }

    public void setDuration(double duration) {
        this.duration.setValue(duration);
        calculateCalories();
    }
}
