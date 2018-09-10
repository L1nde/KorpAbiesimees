package ee.skyhigh.l1nde.korplaitused.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

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

    public void insert(LaitusedEntity laitusedEntity){
        new insertAsyncTask(laitusedDao).execute(laitusedEntity);

    }
    private static class insertAsyncTask extends AsyncTask<LaitusedEntity, Void, Void>{

        private LaitusedDao laitusedDao;

        insertAsyncTask(LaitusedDao laitusedDao){
            this.laitusedDao = laitusedDao;
        }

        @Override
        protected Void doInBackground(LaitusedEntity... laitusedEntities) {
            laitusedDao.insert(laitusedEntities[0]);
            return null;
        }
    }

}
