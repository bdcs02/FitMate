package hu.nje.fitmate.ui.session.sessionlist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hu.nje.fitmate.R;

public class SessionHolder extends RecyclerView.ViewHolder {
    public TextView startTimeTextView;

    public TextView sessionDescTextView;

    public TextView burnedCaloriesTextView;

    public SessionHolder(@NonNull View itemView) {
        super(itemView);
        this.startTimeTextView = itemView.findViewById(R.id.startTimeTextView);
        this.sessionDescTextView = itemView.findViewById(R.id.sessionDescTextView);
        this.burnedCaloriesTextView = itemView.findViewById(R.id.burnedCaloriesTextView);
    }
}
