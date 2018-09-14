package ee.skyhigh.l1nde.korplaitused;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.adapter.MembersAdapter;
import ee.skyhigh.l1nde.korplaitused.data.KorpViewModel;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public class Members extends AppCompatActivity {

    private KorpViewModel korpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        RecyclerView memberList = findViewById(R.id.memberList);
        memberList.setHasFixedSize(true);

        memberList.setLayoutManager(new LinearLayoutManager(this));
        final MembersAdapter adapter = new MembersAdapter();
        memberList.setAdapter(adapter);

        korpViewModel = ViewModelProviders.of(this).get(KorpViewModel.class);
        korpViewModel.getMembers().observe(this, new Observer<List<MemberEntity>>() {
            @Override
            public void onChanged(@Nullable List<MemberEntity> memberEntities) {
                adapter.setMembers(memberEntities);
            }
        });
    }

    public void addMember(View view){
        Intent intent = new Intent(this, AddMember.class);
        startActivity(intent);
    }
}
