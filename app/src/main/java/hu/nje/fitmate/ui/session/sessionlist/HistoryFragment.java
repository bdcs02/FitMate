package hu.nje.fitmate.ui.session.sessionlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import hu.nje.fitmate.database.AppDatabase;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.R;
import hu.nje.fitmate.database.models.Session;

public class HistoryFragment extends Fragment {

    private Button backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        // datasource
        List<Session> sessionList = getAppdatabase().sessionDao().getAllSessions();

        // init list control
        RecyclerView recyclerView = view.findViewById(R.id.sessionRecycleView);

        // adapter
        SessionListAdapter sessionListAdapter = new SessionListAdapter(sessionList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(sessionListAdapter);


        return view;
    }

    private AppDatabase getAppdatabase() {
        return ((MainActivity)getActivity()).getAppDatabase();
    }

    private NavController getNavController() {
        return ((MainActivity)getActivity()).getNavController();
    }
}