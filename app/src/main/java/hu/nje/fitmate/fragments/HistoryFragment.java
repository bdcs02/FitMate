package hu.nje.fitmate.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.R;

public class HistoryFragment extends Fragment {

    private Button backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        backButton = view.findViewById(R.id.btBackHistory);
        backButton.setOnClickListener(v -> {
            getNavController().navigate(R.id.homeFragment);
        });

        return view;
    }

    private NavController getNavController() {
        return ((MainActivity)getActivity()).getNavController();
    }
}