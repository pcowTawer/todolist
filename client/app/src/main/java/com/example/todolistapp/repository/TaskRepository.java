package com.example.todolistapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.todolistapp.model.TaskModel;
import com.example.todolistapp.retrofit.ApiInterface;
import com.example.todolistapp.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepository {
    private static final ApiInterface myInterface = RetrofitService.getApiInterface();
    private final MutableLiveData<ArrayList<TaskModel>> taskListLiveData = new MutableLiveData<>();
    private static TaskRepository taskRepository;

    public static TaskRepository getInstance(){
        if (taskRepository == null){
            taskRepository = new TaskRepository();
        }
        return taskRepository;
    }

    public MutableLiveData<ArrayList<TaskModel>> getTasks() {
        myInterface.getTasks().enqueue(new Callback<ArrayList<TaskModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<TaskModel>> call, @NonNull Response<ArrayList<TaskModel>> response) {
                Log.d("Response", "Get all tasks ");
                taskListLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<TaskModel>> call, @NonNull Throwable throwable) {
                taskListLiveData.setValue(new ArrayList<>());
                Log.d("Response failure", Objects.requireNonNull(throwable.getMessage()));
            }
        });
        return taskListLiveData;
    }

    public void addTask(TaskModel task) {
        myInterface.addTask(task).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.d("Response", "Add task with title: " + task.getTitle());
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                Log.d("Response failure", Objects.requireNonNull(throwable.getMessage()));
            }
        });
    }

    public void updateTask(int id, TaskModel newTask) {
        myInterface.updateTask(id, newTask).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.d("Response", "Update task with id: " + id );
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                Log.d("Response failure", Objects.requireNonNull(throwable.getMessage()));
            }
        });
    }

    public void deleteTask(int id) {
        myInterface.deleteTask(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.d("Response", "Delete task with id:" + id );
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                Log.d("Response failure", Objects.requireNonNull(throwable.getMessage()));
            }
        });
    }
}
