package edu.tamu.adamhair.experiencesamplingsurvey;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by adamhair on 11/11/15.
 * Following instructions from Tutorialspoint
 */
public class DisplayResults extends AppCompatActivity{
    int from_where_i_am_coming = 0;
    private DBHelper db;

    TextView response;
    TextView likert;
    int id_to_update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        response = (TextView)findViewById(R.id.response);
        likert = (TextView)findViewById(R.id.likert);

        db = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");

            if(Value>0)
            {
                Cursor rs = db.getData(Value);
                id_to_update = Value;
                rs.moveToFirst();

                String surveyResponse = rs.getString(rs.getColumnIndex(DBHelper.TABLE_ROW_ONE));
                int likertResponse = rs.getInt(rs.getColumnIndex(DBHelper.TABLE_ROW_TWO));

                response.setText(surveyResponse);
                likert.setText(likertResponse + "");

                if (!rs.isClosed())
                {
                    rs.close();
                }
            }
        }
    }
}
