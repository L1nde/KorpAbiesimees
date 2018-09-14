package ee.skyhigh.l1nde.korplaitused.listeners;

import ee.skyhigh.l1nde.korplaitused.data.entites.LaitusedEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public interface ActivityListener {
    void updateAttendanceDB(long memberId, String checked);
    void updateLateDB(long memberId, boolean checked);
    void updateLaitusIncDB(long memberId);
    void updateLaitusDecDB(long memberId);
    void updateMarkusIncDB(long memberId);
    void updateMarkusDecDB(long memberId);
    LaitusedEntity createFindLaitus(long memberId);
}
