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

    public void update(MemberEntity memberEntity){
        new updateAsyncTask(memberDao).execute(memberEntity);
    }

    public MemberEntity findMember(long id){
        try {
            return new selectAsyncTask(memberDao).execute(id).get();
        } catch (ExecutionException | InterruptedException e) {
            Log.e(this.getClass().getSimpleName(), "Member select error", e);
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

    private static class updateAsyncTask extends AsyncTask<MemberEntity, Void, Void>{

        private MemberDao memberDao;

        updateAsyncTask(MemberDao memberDao){
            this.memberDao = memberDao;
        }

        @Override
        protected Void doInBackground(MemberEntity... memberEntities) {
            memberDao.update(memberEntities[0]);
            return null;
        }
    }

    private static class selectAsyncTask extends AsyncTask<Long, Void, MemberEntity>{

        private MemberDao memberDao;

        selectAsyncTask(MemberDao memberDao){
            this.memberDao = memberDao;
        }

        @Override
        protected MemberEntity doInBackground(Long... ids) {
            return memberDao.findMember(ids[0]);
        }
    }

}
