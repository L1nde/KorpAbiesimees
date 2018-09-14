package ee.skyhigh.l1nde.korplaitused;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ee.skyhigh.l1nde.korplaitused.data.KorpViewModel;
import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public class AddMeeting extends AppCompatActivity {

    private KorpViewModel korpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        korpViewModel = ViewModelProviders.of(this).get(KorpViewModel.class);
    }

    public void saveMeeting(View view){
        EditText date = findViewById(R.id.dateField);

        RadioGroup meetingTypeGroup = findViewById(R.id.meetingTypeGroup);

        RadioButton checkedRadio = findViewById(meetingTypeGroup.getCheckedRadioButtonId());

        if (!date.getText().toString().isEmpty() && checkedRadio != null){
            MeetingEntity meetingEntity = new MeetingEntity(date.getText().toString(), checkedRadio.getText().toString());
            long id = korpViewModel.insertMeeting(meetingEntity);
            Intent intent = new Intent(this, NewMeeting.class);
            intent.putExtra("meetingId", id);
            startActivity(intent);
            finish();
        }
    }
}
