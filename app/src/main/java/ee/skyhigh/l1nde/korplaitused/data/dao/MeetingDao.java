package ee.skyhigh.l1nde.korplaitused.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;

@Dao
public interface MeetingDao {

    @Insert
    void insert(MeetingEntity meetingEntity);

    @Delete
    void delete(MeetingEntity meetingEntity);

    @Query("delete from meetings")
    void deleteAll();

    @Query("SELECT * FROM meetings")
    LiveData<List<MeetingEntity>> getAllMeetings();


}
