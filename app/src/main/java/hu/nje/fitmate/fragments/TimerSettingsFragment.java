package hu.nje.fitmate.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.database.AppDatabase;
import hu.nje.fitmate.database.models.Category;
import hu.nje.fitmate.viewmodels.TimerSettingsViewModel;
import hu.nje.fitmate.R;


public class TimerSettingsFragment extends Fragment {

    Button backButton;
    Button startButton;

    Button exerciseTimeButton;
    Button restTimeButton;

    Spinner exerciseTypeSpinner;

    TimerSettingsViewModel timerSettingsViewModel;
    int minuteText;
    int secondText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer_settings, container, false);

        timerSettingsViewModel = new ViewModelProvider(getActivity()).get(TimerSettingsViewModel.class);

        backButton = view.findViewById(R.id.backButton);
        startButton = view.findViewById(R.id.startButton);
        exerciseTimeButton = view.findViewById(R.id.ExerciseTimeButton);
        restTimeButton = view.findViewById(R.id.restTimeButton);
        EditText setsEditText = view.findViewById(R.id.SetsEditTextNumber);
        exerciseTypeSpinner = view.findViewById(R.id.exerciseTypeSpinner);
        Spinner spinner = view.findViewById(R.id.exerciseTypeSpinner);


        /*TESZT ADATOK
        Category category1 = new Category("Futás","Futni jó fiam!");
        Category category2 = new Category("Kocogás","Kocogni is jó fiam!");
        Category category3 = new Category("Sprint","Sprintelni már nem jó fiam!");
        Category category4 = new Category("Gyaloglás","Gyalogolni már jó fiam!");
        Category category5 = new Category("Plank","Ne is kérdezz erről");

        getAppdatabase().categoryDao().insertCategory(category1);
        getAppdatabase().categoryDao().insertCategory(category2);
        getAppdatabase().categoryDao().insertCategory(category3);
        getAppdatabase().categoryDao().insertCategory(category4);
        getAppdatabase().categoryDao().insertCategory(category5);*/

        List<Category> categoryList = getAppdatabase().categoryDao().getCategorys();
        List<String> nameList = new ArrayList<>();
        for (Category category : categoryList) {
            nameList.add(category.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, nameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                String selectedName = nameList.get(position);
                timerSettingsViewModel.getExerciseType().setValue(selectedName);
                Toast.makeText(getContext(), "Selected Name: " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        backButton.setOnClickListener(v -> {
            getNavController().navigate(R.id.homeFragment);
        });

        startButton.setOnClickListener(v -> {
            if(!setsEditText.getText().toString().isEmpty())
            {
                timerSettingsViewModel.getSets().setValue(Integer.parseInt(setsEditText.getText().toString()));
            }
            getNavController().navigate(R.id.action_timerSettingsFragment_to_timerFragment);
        });

        exerciseTimeButton.setOnClickListener(v -> {
            TimePicker(true);
        });

        restTimeButton.setOnClickListener(v -> {
            TimePicker(false);
        });

        return view;
    }

    private NavController getNavController() {
        return ((MainActivity)getActivity()).getNavController();
    }

    private AppDatabase getAppdatabase() {
        return ((MainActivity)getActivity()).getAppDatabase();
    }

    public void TimePicker(boolean exerciseSelect)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {


            @Override
            public void onTimeSet(TimePicker view, int minute, int second) {
                if(exerciseSelect)
                {
                    timerSettingsViewModel.getExerciseTimeMinute().setValue(minute);
                    timerSettingsViewModel.getExerciseTimeSecond().setValue(second);
                    exerciseTimeButton.setText(minute + ":" + ((second > 9) ? second: "0" + second ));
                }
                else
                {
                    timerSettingsViewModel.getRestTimeMinute().setValue(minute);
                    timerSettingsViewModel.getRestTimeSecond().setValue(second);
                    restTimeButton.setText(minute + ":" + ((second > 9) ? second: "0" + second));
                }
                minuteText = minute;
                secondText = second;
            }
        };

        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(getActivity(),onTimeSetListener,minuteText,secondText,true);
        timePickerDialog.show();
    }
}