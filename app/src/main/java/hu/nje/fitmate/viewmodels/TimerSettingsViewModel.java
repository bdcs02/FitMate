package hu.nje.fitmate.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimerSettingsViewModel extends ViewModel {

    private final MutableLiveData<Integer> exerciseTimeSecond = new MutableLiveData<>();
    private final MutableLiveData<Integer> exerciseTimeMinute = new MutableLiveData<>();

    private final MutableLiveData<Integer> restTimeSecond = new MutableLiveData<>();
    private final MutableLiveData<Integer> restTimeMinute = new MutableLiveData<>();

    private final MutableLiveData<Integer> sets = new MutableLiveData<>();


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

    public MutableLiveData<Integer> getSets() {
        return sets;
    }

    public void SetValues(){
        if(getExerciseTimeMinute().getValue() == null) {exerciseTimeMinute.setValue(5);}
        if(getExerciseTimeSecond().getValue() == null) {exerciseTimeSecond.setValue(0);}
        if(getRestTimeMinute().getValue() == null){restTimeMinute.setValue(2);}
        if(getRestTimeSecond().getValue() == null){restTimeSecond.setValue(0);}
        if(getSets().getValue() == null){sets.setValue(5);}
    }
}
