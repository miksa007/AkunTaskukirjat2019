package fi.tuni.miksa.taskari2019;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String LOG="softa:Mainactivity";
    private TaskukirjaViewModel mTaskukirjaViewModel;
    public static final int NEW_TASKUKIRJA_ACTIVITY_REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tapahtui + nappulan painallus
                Intent intent = new Intent(MainActivity.this, NewTaskukirjaActivity.class);
                startActivityForResult(intent, NEW_TASKUKIRJA_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TaskukirjaListAdapter adapter = new TaskukirjaListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mTaskukirjaViewModel= ViewModelProviders.of(this).get(TaskukirjaViewModel.class);

        mTaskukirjaViewModel.getKaikkiTaskukirjat().observe(this, new Observer<List<Taskukirja>>() {
            @Override
            public void onChanged(List<Taskukirja> taskukirjas) {
                adapter.setmTaskukirjat(taskukirjas);
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TASKUKIRJA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String arvot=data.getStringExtra(NewTaskukirjaActivity.EXTRA_REPLY);
            Log.d(LOG, arvot);
            String[] osa=arvot.split(";");
            int numero=Integer.parseInt(osa[0]);

            Taskukirja taskukirja=new Taskukirja(numero, osa[1]);
            Log.d(LOG,taskukirja.toString());
            mTaskukirjaViewModel.insert(taskukirja);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
