package com.taskfoundation.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;

    ArrayList<Integer> answers = new ArrayList<Integer>();

    int locationOfCorrectAnswer;

    TextView resultTextView;

    int score = 0;
    int numberOfQuestions = 0;

    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout bodyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        bodyLayout = findViewById(R.id.bodyLayout);

        goButton = findViewById(R.id.goButton);

        bodyLayout.setVisibility(View.INVISIBLE);

        goButton.setVisibility(View.VISIBLE);

    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        bodyLayout.setVisibility(View.VISIBLE);
        playAgain(playAgainButton);
    }

    public void chooseAnswer(View view) {
        Log.i("TAG:", view.getTag().toString());
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            Log.i("Correct!", "You got it!");
            resultTextView.setText("Correct!");
            score++;
        } else {
            Log.i("Wrong!", ":/");
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;

        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        newQuestion();
    }

    public void newQuestion() {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

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

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        newQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}