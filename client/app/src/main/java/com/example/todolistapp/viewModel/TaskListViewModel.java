package com.example.todolistapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolistapp.model.TaskModel;
import com.example.todolistapp.repository.TaskRepository;

import java.util.List;

public class TaskListViewModel extends ViewModel {
    private MutableLiveData<List<TaskModel>> taskListLiveData;
    private final TaskRepository repository;

    public TaskListViewModel() {
        super();
        repository = TaskRepository.getInstance();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<TaskModel>> getTaskListLiveData() {
        taskListLiveData = repository.getTasks();
        return taskListLiveData;
    }

    public void addTask(TaskModel task) {
        repository.addTask(task);
    }

    public void updateTask(String id, TaskModel task) {
        repository.updateTask(id, task);
    }

    public void deleteTask(String position) {

        repository.deleteTask(position);
        taskListLiveData = repository.getTasks();
    }
}
