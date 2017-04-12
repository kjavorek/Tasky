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

public class AddTaskActivity extends AppCompatActivity {

    public static final String TASK_NAME = "Name";
    public static final String TASK_DESCRIPTION = "Description";
    public static final String TASK_PRIORITY = "Priority";

    TextView tvTaskName, tvTaskDescription;
    EditText etTaskName, etTaskDescription;
    Button bAdd;
    String taskName, taskDescription, selected;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        this.setUpUI();
    }

    private void setUpUI() {
        this.tvTaskName = (TextView) this.findViewById(R.id.tvTaskName);
        this.tvTaskDescription = (TextView) this.findViewById(R.id.tvTaskDescription);
        this.etTaskName = (EditText) this.findViewById(R.id.etTaskName);
        this.etTaskDescription = (EditText) this.findViewById(R.id.etTaskDescription);
        this.spinner = (Spinner)findViewById(R.id.spinner);
        this.bAdd = (Button) this.findViewById(R.id.bAdd);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.priority, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        this.bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                if(etTaskName.getText().toString().matches("")){
                    Toast.makeText(context,"Nothing to add.", Toast.LENGTH_SHORT).show();
                }else {
                    taskName = etTaskName.getText().toString();
                    taskDescription = etTaskDescription.getText().toString();
                    selected=String.valueOf(spinner.getSelectedItem());

                    Intent result = new Intent();
                    result.setClass(getApplicationContext(), ListActivity.class);
                    result.putExtra(TASK_NAME, taskName);
                    result.putExtra(TASK_DESCRIPTION, taskDescription);
                    result.putExtra(TASK_PRIORITY, selected);
                    startActivity(result);
                }
            }
        });
    }
}
