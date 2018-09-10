package ee.skyhigh.l1nde.korplaitused.data.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "meetings")
public class MeetingEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "type")
    private String type;

    public MeetingEntity(int id, String date, String type) {
        this.id = id;
        this.date = date;
        this.type = type;
    }

    @Ignore
    public MeetingEntity(String date, String type) {
        this.date = date;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
