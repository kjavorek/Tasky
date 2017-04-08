package hr.ferit.kristinajavorek.tasky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView lvTaskList;
    Button bAddTask;
    String name, description, priority;
    Integer selectedPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.setUpUI();
    }
    private void setUpUI() {
        this.lvTaskList = (ListView) this.findViewById(R.id.lvTaskList);
        ArrayList<Task> tasks = this.loadTasks();
        Adapter taskAdapter = new Adapter(tasks);
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

            Task task = new Task(name,description,selectedPriority);
            DBHelper.getInstance(getApplicationContext()).insertTask(task);
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

        this.lvTaskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
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
