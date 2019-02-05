package fi.tuni.miksa.taskari2019;

import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TaskukirjaViewModel extends AndroidViewModel {
    public static final String LOG="softa:Task...ViewModel";
    private TaskukirjaRepository mRepository;
    private LiveData<List<Taskukirja>> mKaikkiTaskukirjat;
    public TaskukirjaViewModel(Application application){
        super(application);
        mRepository=new TaskukirjaRepository(application);
        mKaikkiTaskukirjat=mRepository.getKaikkiTaskukirjat();
    }
    LiveData<List<Taskukirja>> getKaikkiTaskukirjat(){
        return mKaikkiTaskukirjat;
    }

    public void insert (Taskukirja taskukirja){
        Log.d(LOG,taskukirja.toString());
        mRepository.insert(taskukirja);
    }


}
