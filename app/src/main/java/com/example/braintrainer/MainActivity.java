package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button playAgainButton;
    TextView TimerTextView;
    TextView sumTextView;
    Button startButton;
    TextView resultTextView;
    TextView correctAns;
    RelativeLayout gameRelativeLayout;
    Random rand = new Random();
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestion = 0;

    public void chooseAnswer(View view) {
        if (!TimerTextView.getText().toString().equals("0s")) {

            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
                score++;
                resultTextView.setText("Correct!");
            } else {
                resultTextView.setText("Wrong!");
            }
            numberOfQuestion++;
            correctAns.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
            generateQuestion();
        }
    }
    public void start(View view){
        startButton.setVisibility(view.INVISIBLE);
        gameRelativeLayout.setVisibility(view.VISIBLE);
        if(TimerTextView.getText().toString().equals("31s")) {
            playAgain(findViewById(R.id.playAgainButton));
        }
    }

    //Creating The new question
    public void generateQuestion(){
        answers.clear();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+ " + "+Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        for(int i=0; i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{
                int incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button2.setText(Integer.toString(answers.get(0)));
        button3.setText(Integer.toString(answers.get(1)));
        button4.setText(Integer.toString(answers.get(2)));
        button5.setText(Integer.toString(answers.get(3)));

    }


    // play Again method

    public void playAgain(View view) {
            score = 0;
            numberOfQuestion = 0;
            TimerTextView.setText("31s");
            correctAns.setText("0/0");
            resultTextView.setText("");
            playAgainButton.setVisibility(view.INVISIBLE);
            generateQuestion();

            new CountDownTimer(31000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    TimerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
                }

                @Override
                public void onFinish() {
                    playAgainButton.setVisibility(View.VISIBLE);
                    TimerTextView.setText("0s");
                    resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
                }
            }.start();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button)findViewById(R.id.startButton);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        sumTextView = (TextView)findViewById(R.id.textView3);
        correctAns = (TextView)findViewById(R.id.correctAns);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        TimerTextView = (TextView)findViewById(R.id.TimerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);

        generateQuestion();
    }
}
