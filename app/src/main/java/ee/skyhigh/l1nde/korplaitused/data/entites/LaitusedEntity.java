package ee.skyhigh.l1nde.korplaitused.data.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "laitused", foreignKeys = {@ForeignKey(entity = MemberEntity.class, parentColumns = "id", childColumns = "memberId"), @ForeignKey(entity = MeetingEntity.class, parentColumns = "id", childColumns = "meetingId")}, indices = {@Index("memberId"), @Index("meetingId")})
public class LaitusedEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "memberId")
    private int memberId;

    @ColumnInfo(name = "meetingId")
    private int meetingId;

    @ColumnInfo(name = "laitused")
    private int laitused;

    @ColumnInfo(name = "markused")
    private int markused;

    @ColumnInfo(name = "hilinemine")
    private boolean hilinemine = false;

    @ColumnInfo(name = "vabandamine")
    private boolean vabandamine;

    @ColumnInfo(name = "kohal")
    private boolean kohal = false;

    public LaitusedEntity(int id, String date, int memberId, int meetingId, int laitused, int markused, boolean hilinemine, boolean vabandamine, boolean kohal) {
        this.id = id;
        this.date = date;
        this.memberId = memberId;
        this.meetingId = meetingId;
        this.laitused = laitused;
        this.markused = markused;
        this.hilinemine = hilinemine;
        this.vabandamine = vabandamine;
        this.kohal = kohal;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLaitused() {
        return laitused;
    }

    public void setLaitused(int laitused) {
        this.laitused = laitused;
    }

    public int getMarkused() {
        return markused;
    }

    public void setMarkused(int markused) {
        this.markused = markused;
    }

    public boolean isHilinemine() {
        return hilinemine;
    }

    public void setHilinemine(boolean hilinemine) {
        this.hilinemine = hilinemine;
    }

    public boolean isKohal() {
        return kohal;
    }

    public void setKohal(boolean kohal) {
        this.kohal = kohal;
    }

    public int getId() {
        return id;
    }

    public boolean isVabandamine() {
        return vabandamine;
    }

    public void setVabandamine(boolean vabandamine) {
        this.vabandamine = vabandamine;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
