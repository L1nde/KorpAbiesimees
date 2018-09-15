package ee.skyhigh.l1nde.korplaitused.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.adapters.NewMeetingAdapter;
import ee.skyhigh.l1nde.korplaitused.data.KorpViewModel;
import ee.skyhigh.l1nde.korplaitused.data.entites.LaitusedEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;
import ee.skyhigh.l1nde.korplaitused.listeners.MeetingListener;

public class NewMeeting extends AppCompatActivity implements MeetingListener {

    private KorpViewModel korpViewModel;

    private long meetingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meeting);

        Intent intent = getIntent();
        meetingId = intent.getLongExtra("meetingId", -1);

        RecyclerView membersList = findViewById(R.id.membersList);
        membersList.setHasFixedSize(true);

        membersList.setLayoutManager(new LinearLayoutManager(this));
        final NewMeetingAdapter adapter = new NewMeetingAdapter(this);
        membersList.setAdapter(adapter);

        korpViewModel = ViewModelProviders.of(this).get(KorpViewModel.class);
        korpViewModel.getMembers().observe(this, new Observer<List<MemberEntity>>() {
            @Override
            public void onChanged(@Nullable List<MemberEntity> memberEntities) {
                adapter.setMembers(memberEntities);
            }
        });
    }

    @Override
    public void updateAttendanceDB(long memberId, String checked) {
        LaitusedEntity laitus = createFindLaitus(memberId);
        switch (checked){
            case "Kohal":
                laitus.setKohal(true);
                laitus.setVabandamine(false);
                korpViewModel.updateLaitus(laitus);
                Log.i(this.getLocalClassName(), "Attendance updated(Kohal) " + memberId);
                return;
            case "Vabandas":
                laitus.setKohal(false);
                laitus.setVabandamine(true);
                korpViewModel.updateLaitus(laitus);
                Log.i(this.getLocalClassName(), "Attendance updated(Vabandamine) " + memberId);
                return;
            case "Puudus":
                laitus.setKohal(false);
                laitus.setVabandamine(false);
                korpViewModel.updateLaitus(laitus);
                Log.i(this.getLocalClassName(), "Attendance updated(Puudumine) " + memberId);
                return;
            default:
                Log.e(this.getLocalClassName(), "Attendance error " + memberId);
        }
        Log.e(this.getLocalClassName(), "Attendance error " + memberId);
    }

    @Override
    public void updateLateDB(long memberId, boolean checked) {
        LaitusedEntity laitus = createFindLaitus(memberId);
        laitus.setHilinemine(checked);
        korpViewModel.updateLaitus(laitus);
        Log.i(this.getLocalClassName(), "Late updated " + memberId);
    }

    @Override
    public void updateLaitusIncDB(long memberId) {
        LaitusedEntity laitus = createFindLaitus(memberId);
        laitus.setLaitused(laitus.getLaitused() + 1);
        korpViewModel.updateLaitus(laitus);
        Log.i(this.getLocalClassName(), "Laitus+ updated " + memberId);
    }

    @Override
    public void updateLaitusDecDB(long memberId) {
        LaitusedEntity laitus = createFindLaitus(memberId);
        laitus.setLaitused(laitus.getLaitused() - 1);
        korpViewModel.updateLaitus(laitus);
        Log.i(this.getLocalClassName(), "Laitus- updated " + memberId);
    }

    @Override
    public void updateMarkusIncDB(long memberId) {
        LaitusedEntity laitus = createFindLaitus(memberId);
        laitus.setMarkused(laitus.getMarkused() + 1);
        korpViewModel.updateLaitus(laitus);
        Log.i(this.getLocalClassName(), "Markus+ updated " + memberId);

    }

    @Override
    public void updateMarkusDecDB(long memberId) {
        LaitusedEntity laitus = createFindLaitus(memberId);
        laitus.setMarkused(laitus.getMarkused() - 1);
        korpViewModel.updateLaitus(laitus);
        Log.i(this.getLocalClassName(), "Markus- updated " + memberId);

    }

    @Override
    public LaitusedEntity createFindLaitus(long memberId){
        LaitusedEntity laitus = korpViewModel.findLaitusForMemberAndMeeting(memberId, meetingId);
        if (laitus == null){
            laitus = new LaitusedEntity(memberId, meetingId);
            korpViewModel.insertLaitus(laitus);
        }
        return laitus;
    }
}
