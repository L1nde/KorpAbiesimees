package ee.skyhigh.l1nde.korplaitused.data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import ee.skyhigh.l1nde.korplaitused.data.dao.LaitusedDao;
import ee.skyhigh.l1nde.korplaitused.data.dao.MeetingDao;
import ee.skyhigh.l1nde.korplaitused.data.dao.MemberDao;
import ee.skyhigh.l1nde.korplaitused.data.entites.LaitusedEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;


@Database(entities = {MemberEntity.class, LaitusedEntity.class, MeetingEntity.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract MemberDao memberDao();
    public abstract LaitusedDao laitusedDao();
    public abstract MeetingDao meetingDao();


    public static AppDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "korp").fallbackToDestructiveMigration().addCallback(korpDBCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback korpDBCallback = new RoomDatabase.Callback(){

        @Override
        public  void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
//            new PopulateDBAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void>{

        private MemberDao memberDao;

        private LaitusedDao laitusedDao;
        private MeetingDao meetingDao;

        PopulateDBAsync(AppDatabase appDatabase){
            memberDao = appDatabase.memberDao();
            laitusedDao = appDatabase.laitusedDao();
            meetingDao = appDatabase.meetingDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            memberDao.deleteAll();
            laitusedDao.deleteAll();
            meetingDao.deleteAll();
            return null;
        }
    }

}
