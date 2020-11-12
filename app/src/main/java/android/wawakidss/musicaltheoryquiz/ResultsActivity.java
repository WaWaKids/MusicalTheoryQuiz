package android.wawakidss.musicaltheoryquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultsActivity extends AppCompatActivity {

    private Button toMenuButton;
    private Button againButton;
    private TextView resultTextView;
    private int correctAnswers;
    private int wholeQuestions;
    private static final String TAG = "ResultsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);

        Log.d(TAG, "onCreate(Bundle) called");

        Bundle args = getIntent().getExtras();
        toMenuButton = (Button)findViewById(R.id.menu_button);
        againButton = (Button)findViewById(R.id.again_button);
        resultTextView = (TextView)findViewById(R.id.result_view);
        correctAnswers = (int)args.get("correct_answers");
        wholeQuestions = (int)args.get("whole_questions");

        resultTextView.setText("Ты ответил(а) правильно на " + Double.toString(
                (correctAnswers/wholeQuestions)*100) + "% вопросов");

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultsActivity.this, QuestionActivity.class);
                startActivity(intent);
                Log.d(TAG, "Going to QuestionActivity", new Exception());
            }
        });
    }

    public static Intent newIntent(Context packageContext, int correctAnswers, int wholeQuestions) {
        Intent intent = new Intent(packageContext, ResultsActivity.class);
        intent.putExtra("correct_answers", correctAnswers);
        intent.putExtra("whole_questions", wholeQuestions);
        return intent;
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
}