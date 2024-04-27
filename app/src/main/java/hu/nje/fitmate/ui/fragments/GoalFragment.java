package hu.nje.fitmate.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.R;
import hu.nje.fitmate.viewmodels.GoalViewModel;

public class GoalFragment extends Fragment {

    private GoalViewModel viewModel;
    private Button btnCancel;

    private Button btnSave;

    private EditText editTextWeight;

    private EditText editTextDuration;

    private SeekBar seekBarDuration;

    private TextView textViewBurnedCalories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_goal, container, false);

        viewModel = new ViewModelProvider(this).get(GoalViewModel.class);

        btnCancel = view.findViewById(R.id.btnCancel);
        btnSave = view.findViewById(R.id.btnSave);
        editTextWeight = view.findViewById(R.id.editTextWeight);
        editTextDuration = view.findViewById(R.id.editTextDuration);
        seekBarDuration = view.findViewById(R.id.seekBarDuration);
        textViewBurnedCalories = view.findViewById(R.id.tvBurnedCalories);

        viewModel.getBurnedCalories().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double value) {
                textViewBurnedCalories.setText(String.valueOf(value));
            }
        });

        editTextWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    double weight = Double.parseDouble(s.toString());
                    viewModel.setWeight(weight);
                }catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Please enter a valid number!", Toast.LENGTH_SHORT).show();
                    editTextWeight.setText("");
                }

            }
        });

        seekBarDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBarDuration, int progress, boolean fromUser) {
                editTextDuration.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBarDuration) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBarDuration) {
                int finalValue = seekBarDuration.getProgress();
                editTextDuration.setText(String.valueOf(finalValue));
            }
        });

        editTextDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editTextDuration.removeTextChangedListener(this);

                String text = s.toString();
                out: if(!text.isEmpty()) {
                    try {
                        int duration = Integer.parseInt(text);
                        if(duration > seekBarDuration.getMax()) {
                            duration = seekBarDuration.getMax();
                            editTextDuration.setText(String.valueOf(duration));
                            viewModel.setDuration(duration);
                            seekBarDuration.setProgress(duration);
                            break out;
                        }
                        int seekBarProgess = seekBarDuration.getProgress();
                        if(duration != seekBarProgess) {
                            seekBarDuration.setProgress(duration);
                            viewModel.setDuration(duration);
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Please enter a valid number!", Toast.LENGTH_SHORT).show();
                        editTextDuration.setText("");
                        viewModel.setDuration(0);
                        seekBarDuration.setProgress(0);
                    }
                }

                // reattached listener
                editTextDuration.addTextChangedListener(this);
                // set cursor at the end
                editTextDuration.setSelection(editTextDuration.getText().length());
            }
        });

        btnCancel.setOnClickListener(v -> {
            getNavController().navigate(R.id.homeFragment);
        });

        return view;
    }

    private NavController getNavController() {
        return ((MainActivity)getActivity()).getNavController();
    }


}