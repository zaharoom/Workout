package com.study.zaharin.workout;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity
        implements WorkoutListFragment.WorkoutListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onItemClicked(long id) {
        View v = findViewById(R.id.fragment_container);
        if(v != null) {
            WorkoutDetailFragment fragment = new WorkoutDetailFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            fragment.setWorkout(id);
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        } else {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, id);
            startActivity(intent);
        }
    }
}
