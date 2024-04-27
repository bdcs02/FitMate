package hu.nje.fitmate.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.R;

public class GoalFragment extends Fragment {

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

        btnCancel = view.findViewById(R.id.btnCancel);

        // button cancel
        btnCancel.setOnClickListener(v -> {
            getNavController().navigate(R.id.homeFragment);
        });

        return view;
    }

    private NavController getNavController() {
        return ((MainActivity)getActivity()).getNavController();
    }





}