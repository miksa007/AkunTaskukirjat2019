package fi.tuni.miksa.taskari2019;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TaskukirjaDao {
    @Insert
    void insert(Taskukirja taskukirja);

    @Query("DELETE FROM taskukirja_table")
    void deleteAll();

    /**
     * HUOMIO
     * If you, the developer, want to update the stored data, you must use MutableLiveData instead
     * of LiveData. The MutableLiveData class has two public methods that allow you to set the value
     * of a LiveData object, setValue(T) and postValue(T). Usually, MutableLiveData is used in the
     * ViewModel, and then the ViewModel only exposes immutable LiveData objects to the observers.
     *
     * @return
     */

    @Query("SELECT * from taskukirja_table ORDER BY numero ASC")
    LiveData<List<Taskukirja>> getKaikkiTaskukirjat();

}
