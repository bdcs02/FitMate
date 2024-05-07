package hu.nje.fitmate.viewmodels;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TimerToStatsViewModel extends ViewModel {
    private final MutableLiveData<String> startTime = new MutableLiveData<>();
    private final MutableLiveData<String> endTime = new MutableLiveData<>();
    private final MutableLiveData<Float> duration = new MutableLiveData<>();



    private final MutableLiveData<Float> gpsMaximumSpeed = new MutableLiveData<>();
    private final MutableLiveData<Float> gpsMinimumSpeed = new MutableLiveData<>();
    private final MutableLiveData<Float> gpsAverageSpeed = new MutableLiveData<>();

    public MutableLiveData<String> getStartTime() {
        return startTime;
    }

    public MutableLiveData<String> getEndTime() {
        return endTime;
    }

    public MutableLiveData<Float> getDuration() {
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
