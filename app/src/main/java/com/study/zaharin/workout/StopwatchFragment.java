package com.study.zaharin.workout;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchFragment extends Fragment implements View.OnClickListener {

    public static final String EXTRA_SECONDS = "seconds";
    public static final String EXTRA_RUNNING = "running";
    public static final String EXTRA_WAS_RUNNING = "was_running";

    private int seconds = 0;
    private boolean running = false;
    private boolean wasRunning = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        Button startButton = (Button) v.findViewById(R.id.start_button);
        Button stopButton = (Button) v.findViewById(R.id.stop_button);
        Button resetButton = (Button) v.findViewById(R.id.reset_button);

        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        runTimer(v);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(EXTRA_SECONDS);
            running = savedInstanceState.getBoolean(EXTRA_RUNNING);
            wasRunning = savedInstanceState.getBoolean(EXTRA_WAS_RUNNING);
        }
    }

    private void runTimer(View v) {
        final TextView textView = (TextView) v.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;
                String time = String.format(Locale.US, "%d:%02d:%02d", hours, minutes, sec);
                assert textView != null;
                textView.setText(time);

                if (running) seconds++;

                handler.postDelayed(this, 1000);
            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.start_button:
                running = true;
                break;
            case R.id.stop_button:
                running = false;
                break;
            case R.id.reset_button:
                running = false;
                seconds = 0;
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(EXTRA_SECONDS, seconds);
        outState.putBoolean(EXTRA_RUNNING, running);
        outState.putBoolean(EXTRA_WAS_RUNNING, wasRunning);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

}
