package android.wawakidss.musicaltheoryquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private TextView questionTextView;
    private static final String TAG = "QuestionActivity";
    private static final String KEY_INDEX = "index";
    private int currentIndex;
    private int correctAnswers = 0;
    private Question[] questionBank = new Question[] {
            new Question(R.string.dorian_scale_question, R.string.dorian_scale_answer, R.id.false_button),
            new Question(R.string.minor_scale_question, R.string.minor_scale_answer, R.id.true_button),
            new Question(R.string.major_scale_question, R.string.major_scale_answer, R.id.false_button),
            new Question(R.string.C_minor_question, R.string.minor_scale_answer, R.id.true_button),
            new Question(R.string.C_sharp_minor_question, R.string.C_sharp_minor_answer, R.id.true_button)
    };
    private int wholeQuestions = questionBank.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.question_activity);

        if (savedInstanceState != null)
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        questionTextView = (TextView)findViewById(R.id.question_text_view);
        trueButton = (Button)findViewById(R.id.true_button);
        falseButton = (Button)findViewById(R.id.false_button);

        questionTextView.setText(questionBank[currentIndex].getQuestionResId());

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == questionBank[currentIndex].getCorrectButtonId())
                    correctAnswers += 1;
                trueButton.setVisibility(View.GONE);
                falseButton.setVisibility(View.GONE);
                questionTextView.setText(questionBank[currentIndex].getAnswerResId());
                questionTextView.setClickable(true);
            }
        };

        trueButton.setOnClickListener(onClickListener);
        falseButton.setOnClickListener(onClickListener);

        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentIndex < wholeQuestions - 1) {
                    updateQuestion();
                    trueButton.setVisibility(View.VISIBLE);
                    falseButton.setVisibility(View.VISIBLE);
                    questionTextView.setClickable(false);
                }

                else {
                    Intent intent = ResultsActivity.newIntent(
                            QuestionActivity.this, correctAnswers, wholeQuestions);
                    Log.d(TAG, "correct - " +  Integer.toString(correctAnswers) +
                            ", whole - " + Integer.toString(wholeQuestions));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSavedInstanceState");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        currentIndex = (currentIndex + 1) % wholeQuestions;
        questionTextView.setText(questionBank[currentIndex].getQuestionResId());
        Log.d(TAG, "Updating question text", new Exception());
    }
}