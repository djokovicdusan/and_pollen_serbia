package com.example.and_pollen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionnaireActivity extends AppCompatActivity {

        RadioGroup rg1, rg2, rg3,rg4,rg5,rg6;
        private Button falseButton;
        private Button trueButton;
        private int correct = 0;
        private int incorrect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        rg1= findViewById(R.id.rg1);
        rg2= findViewById(R.id.rg2);
        rg3= findViewById(R.id.rg3);
        rg4= findViewById(R.id.rg4);
        rg5= findViewById(R.id.rg5);
        rg6= findViewById(R.id.rg6);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.action_main: {
                finish();

                return true;

            }
            case R.id.action_info: {
                Intent intent = new Intent(getBaseContext(), InfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                return true;

            }

            default:
                return true;
        }

    }


    public void onRadioButtonTrueClicked(View view) {
        correct++;
    }

    public void onRadioButtonFalseClicked(View view) {
        incorrect++;
    }


    public void showResults(View view) {

        if (rg1.getCheckedRadioButtonId() == -1 || rg2.getCheckedRadioButtonId() == -1 || rg3.getCheckedRadioButtonId() == -1 ||
        rg4.getCheckedRadioButtonId() == -1  || rg5.getCheckedRadioButtonId() == -1 || rg6.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(QuestionnaireActivity.this, "All questions must be answered!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent = new Intent(QuestionnaireActivity.this, ResultsQuizActivity.class);
            intent.putExtra("true", correct);
            intent.putExtra("false", incorrect);
            startActivity(intent);
        }


    }
}