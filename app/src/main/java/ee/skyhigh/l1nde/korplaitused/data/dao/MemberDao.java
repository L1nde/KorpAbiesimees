package ee.skyhigh.l1nde.korplaitused.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

@Dao
public interface MemberDao {

    @Insert
    long insert(MemberEntity memberEntity);

    @Delete
    void delete(MemberEntity memberEntity);

    @Update
    void update(MemberEntity memberEntity);

    @Query("SELECT * FROM member where id=:memberId")
    MemberEntity findMember(long memberId);

    @Query("delete from member")
    void deleteAll();

    @Query("SELECT * FROM member")
    LiveData<List<MemberEntity>> getAllMembers();
}
