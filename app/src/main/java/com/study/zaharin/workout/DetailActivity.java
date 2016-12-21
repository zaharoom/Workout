package com.study.zaharin.workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_ID = "wid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment)
                getFragmentManager().findFragmentById(R.id.detail_frag);
        int workoutId = (int) getIntent().getLongExtra(EXTRA_WORKOUT_ID, 0);
        workoutDetailFragment.setWorkout(workoutId);
    }
}
