package ee.skyhigh.l1nde.korplaitused.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

@Dao
public interface MemberDao {

    @Insert
    long insert(MemberEntity memberEntity);

    @Delete
    void delete(MemberEntity memberEntity);

    @Query("delete from member")
    void deleteAll();

    @Query("SELECT * FROM member")
    LiveData<List<MemberEntity>> getAllMembers();

//    @Query("SELECT * FROM member WHERE ")
//    LiveData<MemberEntity> getMemberId(String type, String firstname, String lastname);
}
