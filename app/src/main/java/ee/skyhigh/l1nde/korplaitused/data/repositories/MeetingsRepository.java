package ee.skyhigh.l1nde.korplaitused.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.data.dao.MeetingDao;
import ee.skyhigh.l1nde.korplaitused.data.dao.MemberDao;
import ee.skyhigh.l1nde.korplaitused.data.database.AppDatabase;
import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public class MeetingsRepository {
    private MeetingDao meetingDao;
    private LiveData<List<MeetingEntity>> meetings;

    public MeetingsRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        meetingDao = db.meetingDao();
        meetings = meetingDao.getAllMeetings();
    }

    public LiveData<List<MeetingEntity>> getAllMeetings() {
        return meetings;
    }

    public void insert(MeetingEntity meetingEntity){
        new MeetingsRepository.insertAsyncTask(meetingDao).execute(meetingEntity);

    }
    private static class insertAsyncTask extends AsyncTask<MeetingEntity, Void, Void> {

        private MeetingDao meetingDao;

        insertAsyncTask(MeetingDao meetingDao){
            this.meetingDao = meetingDao;
        }

        @Override
        protected Void doInBackground(MeetingEntity... meetingEntities) {
            meetingDao.insert(meetingEntities[0]);
            return null;
        }
    }


}
