package com.example.todolistapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolistapp.model.TaskModel;
import com.example.todolistapp.repository.TaskRepository;

import java.util.List;

public class TaskListViewModel extends ViewModel {
    public final MutableLiveData<List<TaskModel>> taskListLiveData;
    private final TaskRepository repository;

    public TaskListViewModel() {
        super();
        repository = TaskRepository.getInstance();
        taskListLiveData = new MutableLiveData<>();
        getTasks();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void getTasks() {
        repository.getTasks(taskListLiveData::setValue);
    }

    public void addTask(TaskModel task) {

    }

    public void updateTask(String id, TaskModel task) {

    }

    public void deleteTask(String position) {

    }
}
