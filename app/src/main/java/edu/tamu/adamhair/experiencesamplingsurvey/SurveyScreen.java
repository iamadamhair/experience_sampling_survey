package edu.tamu.adamhair.experiencesamplingsurvey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.Toast;

public class SurveyScreen extends AppCompatActivity {

    EditText questionOne;
    SeekBar likertOne;

    DBHelper db;
    int id_to_update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        questionOne = (EditText)findViewById(R.id.questionOne);
        likertOne = (SeekBar)findViewById(R.id.likertOne);

        db = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            int Value = extras.getInt("id");
            if(Value > 0)
            {
                id_to_update = Value;
            }
        }
    }

    public void save_responses(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            int Value = extras.getInt("id");
            if(Value > 0)
            {
                if(db.update(id_to_update, questionOne.getText().toString(), likertOne.getProgress()))
                {
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Not Saved", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                if(db.insert(questionOne.getText().toString(), likertOne.getProgress()))
                {
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Not Saved", Toast.LENGTH_SHORT).show();
                }
            }
            finish();
        }
    }
}
