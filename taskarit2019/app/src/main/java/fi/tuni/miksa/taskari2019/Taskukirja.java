package fi.tuni.miksa.taskari2019;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "taskukirja_table")
public class Taskukirja {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "numero")
    private int mNumero;

    @NonNull
    @ColumnInfo(name = "nimi")
    private String mNimi;

    public Taskukirja(@NonNull int numero, @NonNull String nimi){
        this.mNumero=numero;
        this.mNimi=nimi;
    }

    public int getMNumero() {
        return mNumero;
    }

    public void setmNumero(int mNumero) {
        this.mNumero = mNumero;
    }

    public String getMNimi() {
        return mNimi;
    }

    public void setmNimi(String mNimi) {
        this.mNimi = mNimi;
    }

    @Override
    public String toString() {
        return "Taskukirja{" +
                "mNumero=" + mNumero +
                ", mNimi='" + mNimi + '\'' +
                '}';
    }
}
