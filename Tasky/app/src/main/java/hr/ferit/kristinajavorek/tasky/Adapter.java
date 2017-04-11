package hr.ferit.kristinajavorek.tasky;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private ArrayList<Task> mTasks;
    public Adapter(ArrayList<Task> tasks) { mTasks = tasks; }
    @Override
    public int getCount() { return this.mTasks.size(); }
    @Override
    public Object getItem(int position) { return this.mTasks.get(position); }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder taskViewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_task, parent, false);
            taskViewHolder = new ViewHolder(convertView);
            convertView.setTag(taskViewHolder);
        }
        else{
            taskViewHolder = (ViewHolder) convertView.getTag();
        }
        Task task = this.mTasks.get(position);
        taskViewHolder.tvTaskTitle.setText(task.getTitle());
        taskViewHolder.tvTaskDescription.setText(task.getDescription());
        int priority = task.getPriority();
        switch (priority){
            case 1:
                taskViewHolder.ivPriority.setImageResource(R.drawable.high);
                taskViewHolder.ivPriority.requestLayout();
                taskViewHolder.ivPriority.getLayoutParams().height = 110;
                taskViewHolder.ivPriority.getLayoutParams().width = 120;
                taskViewHolder.ivPriorityLine.setBackgroundColor(Color.parseColor("#990000"));
                break;
            case 2:
                taskViewHolder.ivPriority.setImageResource(R.drawable.medium);
                taskViewHolder.ivPriority.requestLayout();
                taskViewHolder.ivPriority.getLayoutParams().height = 100;
                taskViewHolder.ivPriority.getLayoutParams().width = 90;
                taskViewHolder.ivPriorityLine.setBackgroundColor(Color.parseColor("#F07611"));
                break;
            default:
                taskViewHolder.ivPriority.setImageResource(R.drawable.low);
                taskViewHolder.ivPriorityLine.setBackgroundColor(Color.parseColor("#429900"));
        }
        //taskViewHolder.tvTaskPriority.setText(String.valueOf(task.getPriority()));
        return convertView;
    }
    public void insert(Task task) {
        this.mTasks.add(task);
        this.notifyDataSetChanged();
    }
    public static class ViewHolder {
        public TextView tvTaskTitle, tvTaskDescription;
        public ImageView ivPriority, ivPriorityLine;
        public ViewHolder(View taskView) {
            tvTaskTitle = (TextView) taskView.findViewById(R.id.tvTaskTitle);
            tvTaskDescription = (TextView) taskView.findViewById(R.id.tvTaskDescription);
            ivPriority = (ImageView) taskView.findViewById(R.id.ivPriority);
            ivPriorityLine= (ImageView) taskView.findViewById(R.id.ivPriorityLine);
            //tvTaskPriority = (TextView) taskView.findViewById(R.id.tvTaskPriority);
        }
    }
    public void deleteAt(int position) {
        this.mTasks.remove(position);
        this.notifyDataSetChanged();
    }
}