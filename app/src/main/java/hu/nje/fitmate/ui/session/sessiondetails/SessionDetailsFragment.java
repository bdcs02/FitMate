package hu.nje.fitmate.ui.session.sessiondetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.R;
import hu.nje.fitmate.database.AppDatabase;
import hu.nje.fitmate.database.models.Session;

public class SessionDetailsFragment extends Fragment {
    private TextView startTimeTextView;
    private TextView endTimeTextView;
    private TextView descTextView;
    private TextView burnedCaloriesTextView;

    private TextView typeExerciseTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_session_details, container, false);

        if(getArguments() != null) {
            int sessionId = getArguments().getInt("sessionId", -1);
            displaySessionDetails(sessionId, view);
        }

        return view;
    }

    private void displaySessionDetails(int sessionId, View view) {
        Session session = getAppdatabase().sessionDao().getSessionById(sessionId);

        startTimeTextView = view.findViewById(R.id.textViewStartTime);
        endTimeTextView = view.findViewById(R.id.textViewEndTime);
        typeExerciseTextView = view.findViewById(R.id.textViewTypeExercise);
        descTextView = view.findViewById(R.id.textViewDesc);
        burnedCaloriesTextView = view.findViewById(R.id.textViewBurnedCalories);

        startTimeTextView.setText(String.valueOf(session.getStartTime()));
        endTimeTextView.setText(String.valueOf(session.getEndTime()));
        typeExerciseTextView.setText("Running");
        descTextView.setText(String.valueOf(session.getSessionDesc()));
        burnedCaloriesTextView.setText(String.valueOf(session.getBurnedCalories() + " kcal"));
    }


    private AppDatabase getAppdatabase() {
        return ((MainActivity)getActivity()).getAppDatabase();
    }
}