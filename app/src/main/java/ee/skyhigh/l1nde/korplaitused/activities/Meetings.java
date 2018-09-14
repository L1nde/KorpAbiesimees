package ee.skyhigh.l1nde.korplaitused.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.adapters.MeetingsAdapter;
import ee.skyhigh.l1nde.korplaitused.data.KorpViewModel;
import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;
import ee.skyhigh.l1nde.korplaitused.listeners.ContextListener;

public class Meetings extends AppCompatActivity implements ContextListener {

    private KorpViewModel korpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);

        RecyclerView meetingsList = findViewById(R.id.meetingList);
        meetingsList.setHasFixedSize(true);

        meetingsList.setLayoutManager(new LinearLayoutManager(this));
        final MeetingsAdapter adapter = new MeetingsAdapter(this);
        meetingsList.setAdapter(adapter);

        korpViewModel = ViewModelProviders.of(this).get(KorpViewModel.class);
        korpViewModel.getMeetings().observe(this, new Observer<List<MeetingEntity>>() {
            @Override
            public void onChanged(@Nullable List<MeetingEntity> meetingEntities) {
                adapter.setMeetings(meetingEntities);
            }
        });
    }

    public void addNewMeeting(View view){
        Intent intent = new Intent(this, AddMeeting.class);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
