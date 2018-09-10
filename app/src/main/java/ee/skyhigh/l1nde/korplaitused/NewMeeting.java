package ee.skyhigh.l1nde.korplaitused;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.data.KorpViewModel;
import ee.skyhigh.l1nde.korplaitused.data.entites.LaitusedEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public class NewMeeting extends AppCompatActivity implements ActivityListener{

    private KorpViewModel korpViewModel;

    private int meetingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meeting);

        Intent intent = getIntent();
        meetingId = intent.getIntExtra("meetingId", -1);

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
    public void updateDatabase(NewMeetingAdapter.ViewHolder holder, MemberEntity member) {
        LaitusedEntity laitus = korpViewModel.findLaitusForMemberAndMeeting(member.getId(), meetingId);
        if (laitus == null){
            laitus = new LaitusedEntity(member.getId(), meetingId);
            korpViewModel.insertLaitus(laitus);
        }
        switch (((RadioButton) findViewById(holder.getAttendance().getCheckedRadioButtonId())).getText().toString()){
            case "Kohal":
                laitus.setKohal(true);
                break;
            case "Vabandas":
                laitus.setVabandamine(true);
                break;
            case "Puudus":
                laitus.setKohal(false);
                break;
            default:
                Log.e(this.getLocalClassName(), "Attendance error");
        }
        korpViewModel.updateLaitus(laitus);
        Log.i(this.getLocalClassName(), "Attendance error");
    }

    private void createNewLaitus(){

    }
}
