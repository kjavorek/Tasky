package hr.ferit.kristinajavorek.tasky;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import static java.lang.String.valueOf;

public class ListActivity extends AppCompatActivity {
    public static final String TASK_ID = "Id";
    public static final String NAME_UPDATE = "Name";
    public static final String DESCRIPTION_UPDATE = "Description";
    public static final String PRIORITY_UPDATE = "Priority";
    ListView lvTaskList;
    Button bAddTask;
    String name, description, priority, id;
    Integer selectedPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.setUpUI();
    }
    private void setUpUI() {
        this.lvTaskList = (ListView) this.findViewById(R.id.lvTaskList);
        final ArrayList<Task> tasks = this.loadTasks();
        final Adapter taskAdapter = new Adapter(tasks);
        this.lvTaskList.setAdapter(taskAdapter);
        this.bAddTask = (Button) this.findViewById(R.id.bAddTask);
        Intent newTaskIntent = this.getIntent();
        if(newTaskIntent.hasExtra(AddTaskActivity.TASK_NAME)){
            name = newTaskIntent.getStringExtra(AddTaskActivity.TASK_NAME);
            description = newTaskIntent.getStringExtra(AddTaskActivity.TASK_DESCRIPTION);
            priority = newTaskIntent.getStringExtra(AddTaskActivity.TASK_PRIORITY);
            if (priority.equals("High")) selectedPriority=1;
            else if (priority.equals("Medium")) selectedPriority=2;
            else selectedPriority=3;
            Task task = new Task(name,description,selectedPriority, 7);
            long id = DBHelper.getInstance(getApplicationContext()).insertTask(task);
            task.setId((int)id);
            Adapter adapter = (Adapter) lvTaskList.getAdapter();
            adapter.insert(task);
        }

        this.bAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AddTaskActivity.class);
                startActivity(intent);
            }
        });

        this.lvTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), UpdateTaskActivity.class);
                Task task = (Task)taskAdapter.getItem(position);
                intent.putExtra(TASK_ID,valueOf(task.getId()));
                intent.putExtra(NAME_UPDATE,task.getTitle());
                intent.putExtra(DESCRIPTION_UPDATE,task.getDescription());
                intent.putExtra(PRIORITY_UPDATE,valueOf(task.getPriority()));
                startActivity(intent);
            }
        });

        this.lvTaskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                Task task = (Task)taskAdapter.getItem(position);
                int mId = task.getId();
                //String selected = ((TextView) view.findViewById(R.id.tvTaskTitle)).getText().toString();
                //Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT).show();
                DBHelper.getInstance(getApplicationContext()).deleteTask(mId);
                Adapter adapter = (Adapter) lvTaskList.getAdapter();
                adapter.deleteAt(position);
                return true;
            }
        });

    }
    private ArrayList<Task> loadTasks() {
        return DBHelper.getInstance(this).getAllTasks();
    }
}
