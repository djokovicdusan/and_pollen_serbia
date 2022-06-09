package com.example.and_pollen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

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
            case R.id.action_quiz: {
                Intent intent = new Intent(getBaseContext(), QuestionnaireActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                return true;
            }
            default:
                return true;
        }

    }


    public void goBack(View view) {
        finish();
    }

    public void takeQuiz(View view) {
        Intent intent = new Intent(InfoActivity.this, QuestionnaireActivity.class);
        startActivity(intent);
    }
}