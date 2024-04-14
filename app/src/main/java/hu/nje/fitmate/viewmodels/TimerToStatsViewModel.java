package hu.nje.fitmate.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimerToStatsViewModel extends ViewModel {
    private final MutableLiveData<String> startTime = new MutableLiveData<>();
    private final MutableLiveData<String> endTime = new MutableLiveData<>();
    private final MutableLiveData<Integer> duration = new MutableLiveData<>();

    private final MutableLiveData<Float> gpsMaximumSpeed = new MutableLiveData<>();
    private final MutableLiveData<Float> gpsMinimumSpeed = new MutableLiveData<>();
    private final MutableLiveData<Float> gpsAverageSpeed = new MutableLiveData<>();

    public MutableLiveData<String> getStartTime() {
        return startTime;
    }

    public MutableLiveData<String> getEndTime() {
        return endTime;
    }

    public MutableLiveData<Integer> getDuration() {
        return duration;
    }

    public MutableLiveData<Float> getGpsMaximumSpeed() {
        return gpsMaximumSpeed;
    }

    public MutableLiveData<Float> getGpsMinimumSpeed() {
        return gpsMinimumSpeed;
    }

    public MutableLiveData<Float> getGpsAverageSpeed() {
        return gpsAverageSpeed;
    }
}
