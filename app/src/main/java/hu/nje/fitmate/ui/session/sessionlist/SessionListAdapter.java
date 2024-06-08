package hu.nje.fitmate.ui.session.sessionlist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.nje.fitmate.MainActivity;
import hu.nje.fitmate.R;
import hu.nje.fitmate.database.models.Session;

public class SessionListAdapter extends RecyclerView.Adapter<SessionHolder> {
    private NavController navController;
    private List<Session> sessionList;

    public SessionListAdapter(List<Session> sessionList, NavController navController) {
        this.navController = navController;
        this.sessionList = sessionList;
    }

    @NonNull
    @Override
    public SessionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.session_list_row, parent, false);

        return new SessionHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SessionHolder holder, int position) {
        Session session = sessionList.get(position);
        holder.startTimeTextView.setText(session.getStartTime());
        holder.burnedCaloriesTextView.setText(String.format("%.2f",session.getBurnedCalories()) + "kcal");
        holder.sessionDescTextView.setText(session.getSessionDesc());

        holder.itemView.setOnClickListener(v -> {
            int sessionId = session.getSessionID();

            System.out.println("gia tri sessionId" + sessionId);

            Bundle bundle = new Bundle();
            bundle.putInt("sessionId", sessionId);

            navController.navigate(R.id.sessionDetailsFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }
}
