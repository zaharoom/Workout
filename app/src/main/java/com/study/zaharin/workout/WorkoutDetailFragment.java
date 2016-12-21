package com.study.zaharin.workout;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {

    private static final String WORKOUT_ID = "workoutId";

    private long workoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            workoutId = savedInstanceState.getLong(WORKOUT_ID);
        }

        StopwatchFragment stopwatchFragment = new StopwatchFragment();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.stopwatch_container, stopwatchFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(WORKOUT_ID, workoutId);
    }

    public void setWorkout(long workoutId) {
        this.workoutId = workoutId;
    }
}
