package ee.skyhigh.l1nde.korplaitused.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ee.skyhigh.l1nde.korplaitused.data.dao.LaitusedDao;
import ee.skyhigh.l1nde.korplaitused.data.database.AppDatabase;
import ee.skyhigh.l1nde.korplaitused.data.entites.LaitusedEntity;


public class LaitusedRepository {

    private LaitusedDao laitusedDao;
    private LiveData<List<LaitusedEntity>> laitused;

    public LaitusedRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        laitusedDao = db.laitusedDao();
        laitused = laitusedDao.getAllLaitused();
    }

    public LiveData<List<LaitusedEntity>> getAllLaitused() {
        return laitused;
    }

    public LaitusedEntity findLaitusForMemberAndMeeting(long memberId, long meetingId){
        try {
            return new selectAsyncTask(memberId, meetingId, laitusedDao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e(this.getClass().getSimpleName(), "findError", e);
        }
        return null;
    }

    public Long insert(LaitusedEntity laitusedEntity){
        try {
            return new insertAsyncTask(laitusedDao).execute(laitusedEntity).get();
        } catch (ExecutionException | InterruptedException e) {
            Log.e(this.getClass().getSimpleName(), "Insert error", e);
        }
        return null;

    }

    public void update(LaitusedEntity laitusedEntity){
        new updateAsyncTask(laitusedDao).execute(laitusedEntity);
    }

    private static class updateAsyncTask extends AsyncTask<LaitusedEntity, Void, Void>{

        private LaitusedDao laitusedDao;

        public updateAsyncTask(LaitusedDao laitusedDao) {
            this.laitusedDao = laitusedDao;
        }

        @Override
        protected Void doInBackground(LaitusedEntity... laitusedEntities) {
            laitusedDao.update(laitusedEntities[0]);
            return null;
        }
    }

    private static class selectAsyncTask extends AsyncTask<Void, Void, LaitusedEntity>{

        private long memberId;
        private long meetingId;

        private LaitusedDao laitusedDao;

        public selectAsyncTask(long memberId, long meetingId, LaitusedDao laitusedDao) {
            this.memberId = memberId;
            this.meetingId = meetingId;
            this.laitusedDao = laitusedDao;
        }

        @Override
        protected LaitusedEntity doInBackground(Void... voids) {
            return laitusedDao.findLaitusForMemberAndMeeting(memberId, meetingId);
        }
    }

    private static class insertAsyncTask extends AsyncTask<LaitusedEntity, Void, Long>{

        private LaitusedDao laitusedDao;

        insertAsyncTask(LaitusedDao laitusedDao){
            this.laitusedDao = laitusedDao;
        }

        @Override
        protected Long doInBackground(LaitusedEntity... laitusedEntities) {
            laitusedDao.insert(laitusedEntities[0]);
            return null;
        }
    }

}
