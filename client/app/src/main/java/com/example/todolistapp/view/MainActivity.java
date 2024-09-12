package com.example.todolistapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.example.todolistapp.R;
import com.example.todolistapp.adapter.TaskAdapter;
import com.example.todolistapp.model.TaskModel;
import com.example.todolistapp.viewModel.TaskListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewTaskDialogListener{

    private TaskListViewModel taskListViewModel;
    private TaskAdapter taskAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        RecyclerView tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        taskListViewModel = new ViewModelProvider(this).get(TaskListViewModel.class);
        taskAdapter = new TaskAdapter(this, taskListViewModel);
        tasksRecyclerView.setAdapter(taskAdapter);
        taskListViewModel.taskListLiveData.observe(this, taskList -> taskAdapter.setTasks((ArrayList<TaskModel>) taskList));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(this, taskAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        FloatingActionButton floatingActionButton = findViewById(R.id.addNewTaskButton);

        floatingActionButton.setOnClickListener(v -> NewTaskDialog.getInstance().show(getSupportFragmentManager(),
        NewTaskDialog.TAG));

    }


    @Override
    public void handleUpdateTask(String id, TaskModel task) {
        taskListViewModel.updateTask(id, task);
    }

    @Override
    public void handleAddTask(TaskModel task) {
        taskListViewModel.addTask(task);
    }
}