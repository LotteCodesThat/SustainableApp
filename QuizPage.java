package com.example.sustainableapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizPage extends AppCompatActivity {
    private Question[] questions = new Question[]{
            new Question("Question 1: Which of these is a benefit of using renewable energy sources?", new Answer[]{
                    new Answer("Reduced greenhouse gas emissions", true),
                    new Answer("Increased greenhouse gas emissions", false),
                    new Answer("Depletion of natural resources", false),
                    new Answer("All of the above", false)
            }),
            new Question("Question 2: What is the impact of e-waste?", new Answer[]{
                    new Answer("Soil degradation", false),
                    new Answer("Water pollution", false),
                    new Answer("Harmful to human health", false),
                    new Answer("All of the above", true)
            }),
            new Question("Question 3: What is a benefit of green computing?", new Answer[]{
                    new Answer("Reduced energy usage", true),
                    new Answer("Increased e-waste", false),
                    new Answer("Higher greenhouse gas emissions", false),
                    new Answer("None of the above", false)
            }),
            new Question("Question 4: What is a challenge of implementing sustainable IT practices?", new Answer[]{
                    new Answer("Cost", true),
                    new Answer("Increased efficiency", false),
                    new Answer("Reduced e-waste", false),
                    new Answer("None of the above", false)
            }),
            new Question("Question 5: How can software contribute to sustainability?", new Answer[]{
                    new Answer("By being inefficient", false),
                    new Answer("By requiring frequent hardware upgrades", false),
                    new Answer("By optimizing resource usage", true),
                    new Answer("None of the above", false)
            })
    };
    private int currentQuestionIndex = 0;
    private int score = 0;

    static class Question {
        String questionText;
        Answer[] answers;

        Question(String questionText, Answer[] answers) {
            this.questionText = questionText;
            this.answers = answers;
        }
    }

    static class Answer {
        String answerText;
        boolean isCorrect;

        Answer(String answerText, boolean isCorrect) {
            this.answerText = answerText;
            this.isCorrect = isCorrect;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        updateQuestion();

        Button[] buttons = new Button[]{
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4)
        };

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (questions[currentQuestionIndex].answers[index].isCorrect) {
                        score += 5;
                    } else {
                        score -= 1;
                    }

                    currentQuestionIndex++;

                    if (currentQuestionIndex < questions.length) {
                        updateQuestion();
                    } else {
                        Intent intent = new Intent(QuizPage.this, FinalScreen.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void updateQuestion() {
        TextView questionTextView = findViewById(R.id.question);
        questionTextView.setText(questions[currentQuestionIndex].questionText);

        Button[] buttons = new Button[]{
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4)
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(questions[currentQuestionIndex].answers[i].answerText);
        }

        TextView scoreTextView = findViewById(R.id.score);
        scoreTextView.setText("Score: " + score);
    }
}
