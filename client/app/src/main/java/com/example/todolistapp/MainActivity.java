package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.todolistapp.adapter.TaskAdapter;
import com.example.todolistapp.model.TaskModel;
import com.example.todolistapp.viewModel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<TaskModel> taskList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        TaskAdapter taskAdapter = new TaskAdapter(taskList);
        tasksRecyclerView.setAdapter(taskAdapter);

        MainActivityViewModel taskViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        taskViewModel.getTaskListLiveData().observe(this, taskListResponse -> {
            taskAdapter.setTasks(taskListResponse);
        });
    }
}