package ee.skyhigh.l1nde.korplaitused.data.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "meetings")
public class MeetingEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "type")
    private String type;

    public MeetingEntity(long id, String date, String type) {
        this.id = id;
        this.date = date;
        this.type = type;
    }

    @Ignore
    public MeetingEntity(String date, String type) {
        this.date = date;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MeetingEntity{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
