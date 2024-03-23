package com.check.threading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.check.threading.R;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.result_text_view);
        startButton = findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Disable the button to prevent multiple clicks
                startButton.setEnabled(false);

                // Display "Loading..." message
                resultTextView.setText("Loading...");

                // Start a new thread for the long-running task
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Simulate a long-running task (e.g., network request or computation)
                        try {
                            Thread.sleep(3000); // Simulating a 3-second task
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Update UI with the result on the main (UI) thread
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                // Enable the button again
                                startButton.setEnabled(true);

                                // Display the result
                                resultTextView.setText("Task completed!");
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
