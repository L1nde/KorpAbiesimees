package ee.skyhigh.l1nde.korplaitused.data.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "member")
public class MemberEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "firstname")
    private String firstname;

    @ColumnInfo(name = "lastname")
    private String lastname;

    @ColumnInfo(name = "type")
    private String type;

    public MemberEntity(long id, String firstname, String lastname, String type) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
    }

    @Ignore
    public MemberEntity(String firstname, String lastname, String type) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MemberEntity{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
