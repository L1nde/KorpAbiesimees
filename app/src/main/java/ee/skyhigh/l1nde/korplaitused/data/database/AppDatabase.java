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


@Database(entities = {MemberEntity.class, LaitusedEntity.class, MeetingEntity.class}, version = 7)
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
//            new PopulateDBAsync(INSTANCE).execute(); //todo remove
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
            deleteAll();
            populateMembers();
            return null;
        }

        private void deleteAll(){
            memberDao.deleteAll();
            laitusedDao.deleteAll();
            meetingDao.deleteAll();
        }

        private void populateMembers(){
            // ksv!
            memberDao.insert(new MemberEntity("Einar", "Linde", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Krister Aleks", "Kasemaa", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Kristo", "Kesler", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Lauri", "Leiten", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Kaarel", "Viikna", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Evar", "Raid", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Jürgen", "Reimus", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Hannes", "Saariste", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Taavi", "Unt", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Andreas", "Rebane", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Jan", "Šinkejev", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Toomas", "Tarmu", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Mehis", "Malts", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Tenno", "Mätlik", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Sergei", "Malinovski", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Arle", "Kõrkjas", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Velis", "Malts", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Marko", "Tiirmaa", "ksv!", true, false));
            memberDao.insert(new MemberEntity("kaur", "Siilbek", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Ardi", "Siilaberg", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Bruno", "Takkel", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Hannes", "Heinsar", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Tarmi", "Hausenberg", "ksv!", true, false));
            memberDao.insert(new MemberEntity("Peeter", "Ever", "ksv!", true, false));

            // reb!
            memberDao.insert(new MemberEntity("Teodor", "Leheste", "reb!", true, false));
            memberDao.insert(new MemberEntity("Karel", "Paan", "reb!", true, false));
            memberDao.insert(new MemberEntity("Lennart", "Maala", "reb!", true, false));
            memberDao.insert(new MemberEntity("Argo", "Jõesoo", "reb!", true, false));

            // bvil!

            memberDao.insert(new MemberEntity("Andres", "Ervin", "bvil!", true, false));


        }
    }

}
