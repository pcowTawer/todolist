package com.example.todolistapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.model.TaskModel;
import com.example.todolistapp.R;

import java.util.ArrayList;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<TaskModel> taskList;

    public TaskAdapter(ArrayList<TaskModel> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskModel item = taskList.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.todoCheckBox.setChecked(item.getCompleted());
    }

    public int getItemCount() {
        return taskList.size();
    }

    public void setTasks(ArrayList<TaskModel> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox todoCheckBox;
        TextView title;
        TextView description;
        ViewHolder(View view) {
            super(view);
            todoCheckBox = view.findViewById(R.id.todoCheckBox);
            title = view.findViewById(R.id.taskTitle);
            description = view.findViewById(R.id.taskDescripiton);
        }
    }
}
