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

    public LaitusedEntity findLaitusForMemberAndMeeting(int memberId, int meetingId){
        return laitusedRepository.findLaitusForMemberAndMeeting(memberId, meetingId);
    }

    public void updateLaitus(LaitusedEntity laitusedEntity){
        laitusedRepository.update(laitusedEntity);
    }

    public void insertMember(MemberEntity memberEntity) {
        membersRepository.insert(memberEntity);
    }

    public void insertLaitus(LaitusedEntity laitusedEntity) {
        laitusedRepository.insert(laitusedEntity);
    }

    public void insertMeeting(MeetingEntity meetingEntity) {
        meetingsRepository.insert(meetingEntity);
    }
}
