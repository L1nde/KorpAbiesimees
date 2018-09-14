package ee.skyhigh.l1nde.korplaitused.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public Long insert(MeetingEntity meetingEntity){
        try {
            return new insertAsyncTask(meetingDao).execute(meetingEntity).get();
        } catch (ExecutionException | InterruptedException e) {
            Log.e(this.getClass().getSimpleName(), "Meeting insert error", e);
        }
        return null;
    }
    private static class insertAsyncTask extends AsyncTask<MeetingEntity, Void, Long> {

        private MeetingDao meetingDao;

        insertAsyncTask(MeetingDao meetingDao){
            this.meetingDao = meetingDao;
        }

        @Override
        protected Long doInBackground(MeetingEntity... meetingEntities) {
            return meetingDao.insert(meetingEntities[0]);

        }
    }


}
