package fi.tuni.miksa.taskari2019;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TaskukirjaListAdapter extends RecyclerView.Adapter<TaskukirjaListAdapter.TaskukirjaViewHolder> {
    public static final String LOG="softa:Tasku...Adapter";
    class TaskukirjaViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskukirjaItemView;

        private TaskukirjaViewHolder(View itemView) {
            super(itemView);
            taskukirjaItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Taskukirja> mTaskukirjat;

    TaskukirjaListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public TaskukirjaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TaskukirjaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskukirjaViewHolder holder, int position) {
        Log.d(LOG, "onBindViewHolder");
        if (mTaskukirjat != null) {
            Log.d(LOG, "onBindViewHolder if");
            Taskukirja current = mTaskukirjat.get(position);
            holder.taskukirjaItemView.setText(current.getMNumero()+" " +current.getMNimi());
        } else {
            // Covers the case of data not being ready yet.
            holder.taskukirjaItemView.setText("No Aku");
        }
    }

    void setmTaskukirjat(List<Taskukirja> taskukirjat){
        mTaskukirjat = taskukirjat;
        notifyDataSetChanged();
    }

    //Ei palauteta null arvoa ekalla kerralla
    @Override
    public int getItemCount() {
        Log.d(LOG,"haetaan maara");
        if (mTaskukirjat != null) {
            Log.d(LOG, "haetaan maara" + mTaskukirjat.size());
            return mTaskukirjat.size();
        }
        else return 0;
    }
}


