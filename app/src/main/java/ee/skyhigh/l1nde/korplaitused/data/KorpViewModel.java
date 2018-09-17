package ee.skyhigh.l1nde.korplaitused.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.data.entites.LaitusedEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;
import ee.skyhigh.l1nde.korplaitused.data.repositories.LaitusedRepository;
import ee.skyhigh.l1nde.korplaitused.data.repositories.MeetingsRepository;
import ee.skyhigh.l1nde.korplaitused.data.repositories.MembersRepository;


public class KorpViewModel extends AndroidViewModel {

    private MembersRepository membersRepository;
    private LiveData<List<MemberEntity>> members;

    private LaitusedRepository laitusedRepository;
    private LiveData<List<LaitusedEntity>> laitused;

    private MeetingsRepository meetingsRepository;
    private LiveData<List<MeetingEntity>> meetings;


    public KorpViewModel(Application application) {
        super(application);
        membersRepository = new MembersRepository(application);
        members = membersRepository.getAllMembers();

        laitusedRepository = new LaitusedRepository(application);
        laitused = laitusedRepository.getAllLaitused();

        meetingsRepository = new MeetingsRepository(application);
        meetings = meetingsRepository.getAllMeetings();
    }

    public LiveData<List<MemberEntity>> getMembers() {
        return members;
    }

    public LiveData<List<LaitusedEntity>> getLaitused() {
        return laitused;
    }

    public LiveData<List<MeetingEntity>> getMeetings() {
        return meetings;
    }

    public LaitusedEntity findLaitusForMemberAndMeeting(long memberId, long meetingId){
        return laitusedRepository.findLaitusForMemberAndMeeting(memberId, meetingId);
    }

    public List<LaitusedEntity> findLaitusedForMember(long memberId){
        return laitusedRepository.findLaitusedForMember(memberId);
    }

    public void updateLaitus(LaitusedEntity laitusedEntity){
        laitusedRepository.update(laitusedEntity);
    }

    public void updateMember(MemberEntity memberEntity){
        membersRepository.update(memberEntity);
    }

    public MemberEntity findMember(long id){
        return membersRepository.findMember(id);
    }



    public long insertMember(MemberEntity memberEntity) {
        return membersRepository.insert(memberEntity);
    }

    public Long insertLaitus(LaitusedEntity laitusedEntity) {
        return laitusedRepository.insert(laitusedEntity);
    }

    public long insertMeeting(MeetingEntity meetingEntity) {
        return meetingsRepository.insert(meetingEntity);
    }
}
