package ee.skyhigh.l1nde.korplaitused.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ee.skyhigh.l1nde.korplaitused.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void membersButton(View view){
        Intent intent = new Intent(this, Members.class);
        startActivity(intent);
    }

    public void meetingsButton(View view){
        Intent intent = new Intent(this, Meetings.class);
        startActivity(intent);
    }
}


