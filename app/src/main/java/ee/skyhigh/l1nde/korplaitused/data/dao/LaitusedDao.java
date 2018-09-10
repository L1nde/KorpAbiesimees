package ee.skyhigh.l1nde.korplaitused.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.data.entites.LaitusedEntity;

@Dao
public interface LaitusedDao {

    @Insert
    void insert(LaitusedEntity laitusedEntity);

    @Update
    void update(LaitusedEntity... repos);

    @Delete
    void delete(LaitusedEntity... repos);

    @Query("delete from laitused")
    void deleteAll();

    @Query("SELECT * FROM laitused")
    LiveData<List<LaitusedEntity>> getAllLaitused();

    @Query("SELECT * FROM laitused WHERE id=:laitusId")
    LiveData<List<LaitusedEntity>> findLaitusedForMember(final int laitusId);
}
