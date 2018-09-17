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

    @ColumnInfo(name = "tartu")
    private boolean tartu;

    @ColumnInfo(name = "away")
    private boolean away;

    public MemberEntity(long id, String firstname, String lastname, String type, boolean tartu, boolean away) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
        this.tartu = tartu;
        this.away = away;
    }

    @Ignore
    public MemberEntity(String firstname, String lastname, String type, boolean tartu, boolean away) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
        this.tartu = tartu;
        this.away = away;
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

    public boolean isTartu() {
        return tartu;
    }

    public boolean isAway() {
        return away;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTartu(boolean tartu) {
        this.tartu = tartu;
    }

    public void setAway(boolean away) {
        this.away = away;
    }

    @Override
    public String toString() {
        return "MemberEntity{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", type='" + type + '\'' +
                ", tartu=" + tartu +
                ", away=" + away +
                '}';
    }
}
