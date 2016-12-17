package com.study.zaharin.workout;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {

    private long workoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View v = getView();
        if (v != null) {
            TextView title = (TextView) v.findViewById(R.id.textTitle);
            TextView description = (TextView) v.findViewById(R.id.textDescription);
            Workout workout = Workout.workouts[(int)workoutId];
            title.setText(workout.getName());
            description.setText(workout.getDescription());
        }
    }

    public void setWorkout(long workoutId) {
        this.workoutId = workoutId;
    }
}
