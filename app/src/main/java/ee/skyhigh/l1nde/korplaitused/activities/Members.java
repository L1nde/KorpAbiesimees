package ee.skyhigh.l1nde.korplaitused.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ee.skyhigh.l1nde.korplaitused.MemberStatistic;
import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.adapters.MembersAdapter;
import ee.skyhigh.l1nde.korplaitused.data.KorpViewModel;
import ee.skyhigh.l1nde.korplaitused.data.entites.LaitusedEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;
import ee.skyhigh.l1nde.korplaitused.listeners.MemberListener;

public class Members extends AppCompatActivity implements MemberListener{

    private KorpViewModel korpViewModel;

    private List<MeetingEntity> meetings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        RecyclerView memberList = findViewById(R.id.memberList);
        memberList.setHasFixedSize(true);

        memberList.setLayoutManager(new LinearLayoutManager(this));
        final MembersAdapter adapter = new MembersAdapter(this);
        memberList.setAdapter(adapter);

        korpViewModel = ViewModelProviders.of(this).get(KorpViewModel.class);
        korpViewModel.getMembers().observe(this, new Observer<List<MemberEntity>>() {
            @Override
            public void onChanged(@Nullable List<MemberEntity> memberEntities) {
                adapter.setMembers(memberEntities);
            }
        });

        korpViewModel.getMeetings().observe(this, new Observer<List<MeetingEntity>>() {
            @Override
            public void onChanged(@Nullable List<MeetingEntity> meetingEntities) {
                meetings = meetingEntities;
            }
        });
    }

    public void addMember(View view){
        Intent intent = new Intent(this, AddMember.class);
        startActivity(intent);
    }

    @Override
    public MemberStatistic getMemberStatistic(long memberId){
        List<LaitusedEntity> laitused  = korpViewModel.findLaitusedForMember(memberId);
        int totalP = 0;
        int totalG = 0;
        for (MeetingEntity meeting : meetings) {
            if (meeting.getType().equals("Erakoosolek"))
                totalP++;
            else
                totalG++;
        }

        int laitusedNr = 0;
        int markusedNr = 0;
        int attendedP = 0;
        int attendedG = 0;
        int late = 0;
        for (LaitusedEntity entity : laitused) {
            boolean isPrivate = isMeetingTypePrivate(entity.getMeetingId());
            if (!entity.isKohal()){
                if (isPrivate){
                    if (!entity.isVabandamine()){
                        laitusedNr++;
                    }
                }
            } else {
                if (isPrivate){
                    attendedP++;
                } else {
                    attendedG++;
                }

            }
            if (entity.isHilinemine()){
                late++;
            }
            laitusedNr += entity.getLaitused() + entity.getMarkused() / 3;
            markusedNr += entity.getMarkused() % 3;
        }
        return new MemberStatistic(laitusedNr, markusedNr, attendedP, attendedG, late, totalP, totalG);
    }

    private boolean isMeetingTypePrivate(long id){
        for (MeetingEntity meeting : meetings) {
            if (meeting.getId() == id){
                return meeting.getType().equals("Erakoosolek");
            }
        }
        return false;
    }



}
