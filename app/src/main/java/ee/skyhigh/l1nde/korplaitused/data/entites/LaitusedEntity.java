package ee.skyhigh.l1nde.korplaitused.data.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "laitused", foreignKeys = {@ForeignKey(entity = MemberEntity.class, parentColumns = "id", childColumns = "memberId"), @ForeignKey(entity = MeetingEntity.class, parentColumns = "id", childColumns = "meetingId")}, indices = {@Index("memberId"), @Index("meetingId")})
public class LaitusedEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "memberId")
    private long memberId;

    @ColumnInfo(name = "meetingId")
    private long meetingId;

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

    public LaitusedEntity(int id, long memberId, long meetingId, int laitused, int markused, boolean hilinemine, boolean vabandamine, boolean kohal) {
        this.id = id;
        this.memberId = memberId;
        this.meetingId = meetingId;
        this.laitused = laitused;
        this.markused = markused;
        this.hilinemine = hilinemine;
        this.vabandamine = vabandamine;
        this.kohal = kohal;
    }

    @Ignore
    public LaitusedEntity(long memberId, long meetingId) {
        this.memberId = memberId;
        this.meetingId = meetingId;
    }

    public long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
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

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "LaitusedEntity{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", meetingId=" + meetingId +
                ", laitused=" + laitused +
                ", markused=" + markused +
                ", hilinemine=" + hilinemine +
                ", vabandamine=" + vabandamine +
                ", kohal=" + kohal +
                '}';
    }
}
