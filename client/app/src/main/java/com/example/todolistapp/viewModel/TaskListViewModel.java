package com.example.todolistapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolistapp.model.TaskModel;
import com.example.todolistapp.repository.TaskRepository;

import java.util.ArrayList;

public class TaskListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TaskModel>> taskListLiveData;
    private final TaskRepository repository;

    public TaskListViewModel() {
        super();
        repository = TaskRepository.getInstance();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<ArrayList<TaskModel>> getTaskListLiveData() {
        taskListLiveData = repository.getTasks();
        return taskListLiveData;
    }

    public void addTask(TaskModel task) {
        repository.addTask(task);
    }

    public void updateTask(int id, TaskModel task) {
        repository.updateTask(id, task);
    }

    public void deleteTask(int position) {

        repository.deleteTask(position);
        taskListLiveData = repository.getTasks();
    }
}
