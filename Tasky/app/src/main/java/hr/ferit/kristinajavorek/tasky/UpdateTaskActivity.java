package hr.ferit.kristinajavorek.tasky;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateTaskActivity extends AppCompatActivity {

    EditText etUpdateTaskName, etUpdateTaskDescription;
    Spinner updateSpinner;
    Button bUpdate;
    String taskNameUpdate, taskDescriptionUpdate, selectedUpdate, selectedId;
    Integer id, updatePriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        this.setUpUI();
    }

    private void setUpUI() {
        this.etUpdateTaskName = (EditText) this.findViewById(R.id.etUpdateTaskName);
        this.etUpdateTaskDescription = (EditText) this.findViewById(R.id.etUpdateTaskDescription);
        this.updateSpinner = (Spinner)findViewById(R.id.updateSpinner);
        this.bUpdate = (Button) this.findViewById(R.id.bUpdate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.priority, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateSpinner.setAdapter(adapter);

        Intent updateTaskIntent = this.getIntent();
        selectedId = updateTaskIntent.getStringExtra(ListActivity.TASK_ID);
        taskNameUpdate = updateTaskIntent.getStringExtra(ListActivity.NAME_UPDATE);
        taskDescriptionUpdate = updateTaskIntent.getStringExtra(ListActivity.DESCRIPTION_UPDATE);
        selectedUpdate = updateTaskIntent.getStringExtra(ListActivity.PRIORITY_UPDATE);

        this.etUpdateTaskName.setText(taskNameUpdate);
        this.etUpdateTaskDescription.setText(taskDescriptionUpdate);
        this.updateSpinner.setSelection(Integer.parseInt(selectedUpdate)-1); //0,1,2
        id=Integer.parseInt(selectedId);

        this.bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                if(etUpdateTaskName.getText().toString().matches("")){
                    Toast.makeText(context,"Nothing to update.", Toast.LENGTH_SHORT).show();
                }else {
                    taskNameUpdate = etUpdateTaskName.getText().toString();
                    taskDescriptionUpdate = etUpdateTaskDescription.getText().toString();
                    selectedUpdate=String.valueOf(updateSpinner.getSelectedItem());
                    if (selectedUpdate.equals("High")) updatePriority=1;
                    else if (selectedUpdate.equals("Medium")) updatePriority=2;
                    else updatePriority=3;
                    DBHelper.getInstance(getApplicationContext()).updateTask(id, taskNameUpdate, taskDescriptionUpdate, updatePriority);
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), ListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
