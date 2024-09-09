package com.example.todolistapp.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.model.TaskModel;
import com.example.todolistapp.R;
import com.example.todolistapp.view.MainActivity;
import com.example.todolistapp.view.NewTaskDialog;
import com.example.todolistapp.viewModel.TaskListViewModel;

import java.util.ArrayList;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<TaskModel> taskList = new ArrayList<>();
    private final MainActivity activity;
    private final TaskListViewModel taskListViewModel;

    public TaskAdapter(MainActivity activity, TaskListViewModel taskListViewModel) {
        this.activity = activity;
        this.taskListViewModel = taskListViewModel;
    }

    public MainActivity getContext() {
        return activity;
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
        holder.todoCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            TaskModel task = new TaskModel();
            task.setTitle(item.getTitle());
            task.setDescription(item.getDescription());
            task.setCompleted(isChecked);
            taskListViewModel.updateTask(item.getId(), task);
        });
    }

    public int getItemCount() {
        return taskList.size();
    }

    public void setTasks(ArrayList<TaskModel> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public void editItem(int position) {
        TaskModel item = taskList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id", item.getId());
        bundle.putString("title", item.getTitle());
        bundle.putString("description", item.getDescription());
        NewTaskDialog fragment = new NewTaskDialog();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), NewTaskDialog.TAG);
    }

    public void deleteItem(int position) {
        TaskModel item = taskList.get(position);
        taskListViewModel.deleteTask(item.getId());
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
