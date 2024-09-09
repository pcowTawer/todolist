package com.example.todolistapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.todolistapp.model.TaskModel;
import com.example.todolistapp.retrofit.ApiInterface;
import com.example.todolistapp.retrofit.RetrofitService;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepository {
    private static final ApiInterface myInterface = RetrofitService.getApiInterface();
    private final MutableLiveData<List<TaskModel>> taskListLiveData = new MutableLiveData<>();
    private static TaskRepository taskRepository;

    public static TaskRepository getInstance(){
        if (taskRepository == null){
            taskRepository = new TaskRepository();
        }
        return taskRepository;
    }

    public MutableLiveData<List<TaskModel>> getTasks() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tasks");
        query.whereExists("title");
        query.findInBackground((objects, e) -> {
            List<TaskModel> taskList = new ArrayList<>();
            for (ParseObject object: objects) {
                TaskModel task = new TaskModel();
                task.setTitle(object.getString("title"));
                task.setDescription(object.getString("description"));
                task.setCompleted(object.getBoolean("completed"));
                task.setId(object.getObjectId());
                taskList.add(task);
            }
            taskListLiveData.setValue(taskList);
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

    public void updateTask(String id, TaskModel newTask) {
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

    public void deleteTask(String id) {
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
