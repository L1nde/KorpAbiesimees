package ee.skyhigh.l1nde.korplaitused;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.data.KorpViewModel;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public class NewMeeting extends AppCompatActivity {

    private KorpViewModel korpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meeting);

        RecyclerView membersList = findViewById(R.id.membersList);
        membersList.setHasFixedSize(true);

        membersList.setLayoutManager(new LinearLayoutManager(this));
        final NewMeetingAdapter adapter = new NewMeetingAdapter();
        membersList.setAdapter(adapter);

        korpViewModel = ViewModelProviders.of(this).get(KorpViewModel.class);
        korpViewModel.getMembers().observe(this, new Observer<List<MemberEntity>>() {
            @Override
            public void onChanged(@Nullable List<MemberEntity> memberEntities) {
                adapter.setMembers(memberEntities);
            }
        });
    }

    public void saveAttendance(View view){
//        EditText firstname = findViewById(R.id.firstname);
//        EditText lastname = findViewById(R.id.lastname);
//
//        RadioGroup statusGroup = findViewById(R.id.statusGroup);
//
//        RadioButton checkedRadio = findViewById(statusGroup.getCheckedRadioButtonId());
//
//        if (!firstname.getText().toString().isEmpty() && !lastname.getText().toString().isEmpty() && checkedRadio != null){
//            korpViewModel.insertMember(new MemberEntity(firstname.getText().toString(), lastname.getText().toString(), checkedRadio.getText().toString()));
//            finish();
//        }
    }
}