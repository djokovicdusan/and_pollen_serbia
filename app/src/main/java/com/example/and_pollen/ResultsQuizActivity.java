package com.example.and_pollen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class ResultsQuizActivity extends AppCompatActivity {

    TextView tvResults;
    PieChart pieChart;
    int answer_true=0;
    int answer_false=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);
        tvResults = findViewById(R.id.rezultatiTekst);
        pieChart = findViewById(R.id.piechart);
        Intent intent = getIntent();
        answer_true= intent.getIntExtra("true", 0);
        answer_false= intent.getIntExtra("false", 0);

        if (answer_true>answer_false){
            tvResults.setText("It looks like you may have allergies pertaining to indoor or outdoor triggers. Given by some of your responses, you may also be allergic to certain kinds of food. It's best to consult a doctor and not rely on self-diagnosis.");
        }else{
            tvResults.setText("Your seasonal profile suggests that you may be affected by weed pollen and might also be affected by mold or fungal spores. These are widespread in certain seasons. It's best to consult a doctor and get some tests done to know for sure.");
        }
        setData();

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

            case R.id.action_info: {
                Intent intent = new Intent(getBaseContext(), InfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                return true;

            }
            case R.id.action_quiz: {
                Intent intent = new Intent(getBaseContext(), QuestionnaireActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                return true;
            }
            default:
                return true;
        }

    }

    private void setData() {

        pieChart.addPieSlice(
                new PieModel(
                        "True",
                        answer_true,
                        Color.parseColor("#7bac02")));
        pieChart.addPieSlice(
                new PieModel(
                        "False",
                        answer_false,
                        Color.parseColor("#FFCC0000")));
        pieChart.startAnimation();
    }
}
