package hu.nje.fitmate;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hu.nje.fitmate.models.TimerSettings;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<Integer> exerciseTimeSecond = new MutableLiveData<>();
    private final MutableLiveData<Integer> exerciseTimeMinute= new MutableLiveData<>();

    private final MutableLiveData<Integer> restTimeSecond= new MutableLiveData<>();
    private final MutableLiveData<Integer> restTimeMinute= new MutableLiveData<>();


    public MutableLiveData<Integer> getExerciseTimeSecond() {
        return exerciseTimeSecond;
    }

    public MutableLiveData<Integer> getExerciseTimeMinute() {
        return exerciseTimeMinute;
    }

    public MutableLiveData<Integer> getRestTimeSecond() {
        return restTimeSecond;
    }

    public MutableLiveData<Integer> getRestTimeMinute() {
        return restTimeMinute;
    }
}