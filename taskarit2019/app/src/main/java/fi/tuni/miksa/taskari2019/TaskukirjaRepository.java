package fi.tuni.miksa.taskari2019;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;

public class TaskukirjaRepository {
    public static final String LOG="softa:Task...Repo";
    private TaskukirjaDao mTaskukirjaDao;
    private LiveData<List<Taskukirja>> mKaikkiTaskukirjat;
    TaskukirjaRepository(Application application) {
        TaskukirjaRoomDatabase db = TaskukirjaRoomDatabase.getDatabase(application);
        mTaskukirjaDao = db.taskukirjaDao();
        mKaikkiTaskukirjat = mTaskukirjaDao.getKaikkiTaskukirjat();
    }
    LiveData<List<Taskukirja>> getKaikkiTaskukirjat(){
        Log.d(LOG, "kaikki haetaan");
        return mKaikkiTaskukirjat;
    }
    public void insert(Taskukirja taskukirja){
        Log.d(LOG, "Aloitetaan asyncTask lisäys");
        new insertAsyncTask(mTaskukirjaDao).execute(taskukirja);
    }
    private static class insertAsyncTask extends AsyncTask<Taskukirja, Void, Void> {

        private TaskukirjaDao mAsyncTaskDao;

        insertAsyncTask(TaskukirjaDao dao) {
            Log.d(LOG, "insert AsyncTask");
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Taskukirja... params) {
            Log.d(LOG,params[0].toString());
            //TODO Tätä on muutettava
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
