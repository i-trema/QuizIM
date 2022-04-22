package com.example.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
        private TextView textViewQuestionNb, textViewQuestion;
        private Button buttonResponse1,buttonResponse2,buttonResponse3,buttonResponse4;
        private QuestionBank questions;
        protected static String score;
        SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewQuestionNb = findViewById(R.id.textViewQuestionNb);
        buttonResponse1 = findViewById(R.id.buttonResponse1);
        buttonResponse2 = findViewById(R.id.buttonResponse2);
        buttonResponse3 = findViewById(R.id.buttonResponse3);
        buttonResponse4 = findViewById(R.id.buttonResponse4);

        buttonResponse1.setTag(0);
        buttonResponse2.setTag(1);
        buttonResponse3.setTag(2);
        buttonResponse4.setTag(3);

        buttonResponse1.setOnClickListener(this);
        buttonResponse2.setOnClickListener(this);
        buttonResponse3.setOnClickListener(this);
        buttonResponse4.setOnClickListener(this);

        questions = DataQuiz.generateQuestions();
        questions.setNextQuestionIndex(0);

        Question question = questions.getNextQuestion();

        Log.i("", (questions.toString()));
        String questionNb = "Question 1/4 :";
        textViewQuestionNb.setText(questionNb);
        textViewQuestion.setText(question.getQuestion());

        buttonResponse1.setText(question.getResponsesList().get(0));
        buttonResponse2.setText(question.getResponsesList().get(1));
        buttonResponse3.setText(question.getResponsesList().get(2));
        buttonResponse4.setText(question.getResponsesList().get(3));

        score="0";
//        enableBtns();
    }

    @Override
    public void onClick(View view) {
        disableBtns();
        int responseIndex = (int) view.getTag();
        int scoreInt = Integer.parseInt(score);

            if (questions.getNextQuestion().getCorrectAnswerIndex() == responseIndex) {
                Toast.makeText(this, "Bonne réponse !", Toast.LENGTH_LONG).show();
                scoreInt++;
                score = String.valueOf(scoreInt);
            } else {
                Toast.makeText(this, "Mauvaise réponse !", Toast.LENGTH_LONG).show();
            }
//            Log.i("score=", score);

        new Handler().postDelayed(() -> {

            if (questions.getNextQuestionIndex() < 3) {
                questions.setNextQuestionIndex(questions.getNextQuestionIndex() + 1);
                Log.i("responseIndex = ", String.valueOf(responseIndex));
                Log.i("Next Question Index =", String.valueOf(questions.getNextQuestionIndex()));

                String questionNb = "Question " + (questions.getNextQuestionIndex() + 1) + "/4 :";
                textViewQuestionNb.setText(questionNb);
                textViewQuestion.setText(questions.getNextQuestion().getQuestion());

                buttonResponse1.setText(questions.getNextQuestion().getResponsesList().get(0));
                buttonResponse2.setText(questions.getNextQuestion().getResponsesList().get(1));
                buttonResponse3.setText(questions.getNextQuestion().getResponsesList().get(2));
                buttonResponse4.setText(questions.getNextQuestion().getResponsesList().get(3));
                enableBtns();
            }else{
                Log.i("","fin du quiz");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Quiz terminé");
                alertDialogBuilder.setMessage("Votre score est de "+score+"/4");
                alertDialogBuilder.setNeutralButton("OK", (dialogInterface, i) -> {

                    questions.setNextQuestionIndex(0);
                    sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);

                    Log.i("nomUserActuel", MainActivity.nomUserActuel);
                    SharedPreferences.Editor edit = sharedPreferences.edit();

                    edit.putString(MainActivity.nomUserActuel, score)
                            .apply();

                    Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                    startActivity(intent);
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

        }, 3000);



    }
    public void enableBtns() {
        buttonResponse1.setEnabled(true);
        buttonResponse2.setEnabled(true);
        buttonResponse2.setEnabled(true);
        buttonResponse2.setEnabled(true);
        Log.i("ok","boutons de réponses activés");
    }
    public void disableBtns() {
        buttonResponse1.setEnabled(false);
        buttonResponse2.setEnabled(false);
        buttonResponse2.setEnabled(false);
        buttonResponse2.setEnabled(false);
        Log.i("ok","boutons de réponses désactivés");
    }
}