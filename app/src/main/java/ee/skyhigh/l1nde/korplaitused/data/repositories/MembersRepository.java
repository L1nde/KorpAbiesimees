package ee.skyhigh.l1nde.korplaitused.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ee.skyhigh.l1nde.korplaitused.data.dao.MemberDao;
import ee.skyhigh.l1nde.korplaitused.data.database.AppDatabase;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;


public class MembersRepository {

    private MemberDao memberDao;
    private LiveData<List<MemberEntity>> members;

    public MembersRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        memberDao = db.memberDao();
        members = memberDao.getAllMembers();
    }

    public LiveData<List<MemberEntity>> getAllMembers() {
        return members;
    }

    public Long insert(MemberEntity memberEntity){
        try {
            return new insertAsyncTask(memberDao).execute(memberEntity).get();
        } catch (ExecutionException | InterruptedException e) {
            Log.e(this.getClass().getSimpleName(), "Member insert error", e);
        }
        return null;

    }
    private static class insertAsyncTask extends AsyncTask<MemberEntity, Void, Long>{

        private MemberDao memberDao;

        insertAsyncTask(MemberDao memberDao){
            this.memberDao = memberDao;
        }

        @Override
        protected Long doInBackground(MemberEntity... memberEntities) {
            return memberDao.insert(memberEntities[0]);
        }
    }

}
