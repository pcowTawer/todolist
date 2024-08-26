package com.example.todolistapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolistapp.model.TaskModel;
import com.example.todolistapp.repository.TaskRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {

    private LiveData<ArrayList<TaskModel>> taskListLiveData;

    public MainActivityViewModel() {
        super();
        TaskRepository repository = TaskRepository.getInstance();
        taskListLiveData = repository.getTasks();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<ArrayList<TaskModel>> getTaskListLiveData() {
        return  taskListLiveData;
    }
}
