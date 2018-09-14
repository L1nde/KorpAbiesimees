package ee.skyhigh.l1nde.korplaitused.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.data.KorpViewModel;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public class AddMember extends AppCompatActivity {

    private KorpViewModel korpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        korpViewModel = ViewModelProviders.of(this).get(KorpViewModel.class);
    }


    public void saveMember(View view){
        EditText firstname = findViewById(R.id.firstname);
        EditText lastname = findViewById(R.id.lastname);

        RadioGroup statusGroup = findViewById(R.id.statusGroup);

        RadioButton checkedRadio = findViewById(statusGroup.getCheckedRadioButtonId());

        if (!firstname.getText().toString().isEmpty() && !lastname.getText().toString().isEmpty() && checkedRadio != null){
            korpViewModel.insertMember(new MemberEntity(firstname.getText().toString(), lastname.getText().toString(), checkedRadio.getText().toString()));
            finish();
        }
    }
}
