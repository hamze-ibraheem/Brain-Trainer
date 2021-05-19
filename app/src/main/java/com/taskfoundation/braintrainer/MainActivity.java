package com.taskfoundation.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;

    ArrayList<Integer> answers = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView sumTextView = findViewById(R.id.sumTextView);
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        goButton = findViewById(R.id.goButton);

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        int locationOfCorrectAnswer = random.nextInt(4);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = random.nextInt(41);

                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
    }
}