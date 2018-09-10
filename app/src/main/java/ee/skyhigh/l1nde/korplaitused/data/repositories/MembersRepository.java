package ee.skyhigh.l1nde.korplaitused.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

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

    public void insert(MemberEntity memberEntity){
        new insertAsyncTask(memberDao).execute(memberEntity);

    }
    private static class insertAsyncTask extends AsyncTask<MemberEntity, Void, Void>{

        private MemberDao memberDao;

        insertAsyncTask(MemberDao memberDao){
            this.memberDao = memberDao;
        }

        @Override
        protected Void doInBackground(MemberEntity... memberEntities) {
            memberDao.insert(memberEntities[0]);
            return null;
        }
    }

}
