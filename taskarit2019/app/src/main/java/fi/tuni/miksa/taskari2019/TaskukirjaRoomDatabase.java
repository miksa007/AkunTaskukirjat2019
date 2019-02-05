package fi.tuni.miksa.taskari2019;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Taskukirja.class}, version = 1)
public abstract class TaskukirjaRoomDatabase extends RoomDatabase {
    public abstract TaskukirjaDao taskukirjaDao();

    private static volatile TaskukirjaRoomDatabase INSTANCE;

    static TaskukirjaRoomDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (TaskukirjaRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            TaskukirjaRoomDatabase.class,
                            "taskukirja_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }

        }
        return INSTANCE;
    }

    /**Alussa turhaa dataa
     *
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TaskukirjaDao mDao;

        PopulateDbAsync(TaskukirjaRoomDatabase db) {
            mDao = db.taskukirjaDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            Log.d("softa", "Ajettiin taas");

            mDao.deleteAll();
            Taskukirja taskukirja=new Taskukirja(1, "Mikki kiipelissä");
            mDao.insert(taskukirja);
            taskukirja=new Taskukirja(2, "Aku Ankka ja Karhukopla");
            mDao.insert(taskukirja);
            return null;
        }
    }
}
