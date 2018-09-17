package ee.skyhigh.l1nde.korplaitused.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.data.KorpViewModel;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public class EditMember extends AppCompatActivity {

    private KorpViewModel korpViewModel;

    private MemberEntity memberEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        korpViewModel = ViewModelProviders.of(this).get(KorpViewModel.class);

        Intent intent = getIntent();
        memberEntity = korpViewModel.findMember(intent.getLongExtra("memberId", -1));

        ((EditText) findViewById(R.id.firstname)).setText(memberEntity.getFirstname());
        ((EditText) findViewById(R.id.lastname)).setText(memberEntity.getLastname());

        RadioGroup statusGroup = findViewById(R.id.statusGroup);
        switch (memberEntity.getType()) {
            case "ksv!":
                statusGroup.check(R.id.ksvRadio);
                break;
            case "reb!":
                statusGroup.check(R.id.rebRadio);
                break;
            case "bvil!":
                statusGroup.check(R.id.bvilRadio);
                break;
            case "lvil!":
                statusGroup.check(R.id.lvilRadio);
                break;
            case "vil!":
                statusGroup.check(R.id.vilRadio);
                break;
        }

        if (memberEntity.isTartu())
            ((RadioGroup) findViewById(R.id.konventGroup)).check(R.id.tartuRadio);
        else if (memberEntity.isTartu())
            ((RadioGroup) findViewById(R.id.konventGroup)).check(R.id.tallinnRadio);

        if (memberEntity.isAway())
            ((CheckBox) findViewById(R.id.awayButton)).setChecked(memberEntity.isAway());


    }

    public void saveMember(View view){
        EditText firstname = findViewById(R.id.firstname);
        EditText lastname = findViewById(R.id.lastname);

        RadioGroup statusGroup = findViewById(R.id.statusGroup);

        RadioButton checkedRadio = findViewById(statusGroup.getCheckedRadioButtonId());

        RadioButton tartuRadio = findViewById(R.id.tartuRadio);

        CheckBox away = findViewById(R.id.awayButton);

        if (!firstname.getText().toString().isEmpty() && !lastname.getText().toString().isEmpty() && checkedRadio != null){
            memberEntity.setFirstname(firstname.getText().toString());
            memberEntity.setLastname(lastname.getText().toString());
            memberEntity.setType(checkedRadio.getText().toString());
            memberEntity.setTartu(tartuRadio.isChecked());
            memberEntity.setAway(away.isChecked());
            korpViewModel.updateMember(memberEntity);
            finish();
        }
    }
}
