package ee.skyhigh.l1nde.korplaitused.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.data.KorpViewModel;
import ee.skyhigh.l1nde.korplaitused.data.entites.LaitusedEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public class AddMeeting extends AppCompatActivity {

    private KorpViewModel korpViewModel;

    private List<MemberEntity> members = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        korpViewModel = ViewModelProviders.of(this).get(KorpViewModel.class);

        korpViewModel.getMembers().observe(this, new Observer<List<MemberEntity>>() {
            @Override
            public void onChanged(@Nullable List<MemberEntity> memberEntities) {
                members = memberEntities;
            }
        });
    }

    public void saveMeeting(View view){
        EditText date = findViewById(R.id.dateField);

        RadioGroup meetingTypeGroup = findViewById(R.id.meetingTypeGroup);

        RadioButton checkedRadio = findViewById(meetingTypeGroup.getCheckedRadioButtonId());

        if (!date.getText().toString().isEmpty() && checkedRadio != null){
            MeetingEntity meetingEntity = new MeetingEntity(date.getText().toString(), checkedRadio.getText().toString());
            long id = korpViewModel.insertMeeting(meetingEntity);
            loadAllMembersLaitused(id);
            Intent intent = new Intent(this, NewMeeting.class);
            intent.putExtra("meetingId", id);
            startActivity(intent);
            finish();
        }
    }

    private void loadAllMembersLaitused(long meetingId){


        for (MemberEntity entity : members) {
            korpViewModel.insertLaitus(new LaitusedEntity(entity.getId(), meetingId));
        }
        Log.i(this.getLocalClassName(), "Created laitused for everyone!");
    }
}
