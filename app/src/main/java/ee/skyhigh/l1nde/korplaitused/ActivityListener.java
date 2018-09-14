package ee.skyhigh.l1nde.korplaitused;

import ee.skyhigh.l1nde.korplaitused.data.entites.LaitusedEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public interface ActivityListener {
    void updateAttendanceDB(MemberEntity member, String checked);
    void updateLateDB(MemberEntity member, boolean checked);
    void updateLaitusIncDB(MemberEntity member);
    void updateLaitusDecDB(MemberEntity member);
    void updateMarkusIncDB(MemberEntity member);
    void updateMarkusDecDB(MemberEntity member);
    LaitusedEntity createFindLaitus(long memberId);
}
