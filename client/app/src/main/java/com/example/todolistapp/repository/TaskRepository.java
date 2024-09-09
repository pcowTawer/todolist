package com.example.todolistapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.todolistapp.model.TaskModel;
import com.example.todolistapp.retrofit.ApiInterface;
import com.example.todolistapp.retrofit.RetrofitService;
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
        ParseObject taskParseObject = new ParseObject("Tasks");
        taskParseObject.put("title", task.getTitle());
        taskParseObject.put("description", task.getDescription());
        taskParseObject.put("completed", task.getCompleted());
        taskParseObject.saveInBackground(e -> {
            if (e == null) {
                Log.d("Response", "Task saved");
            } else {
                Log.d("Response failure", Objects.requireNonNull(e.getLocalizedMessage()));
            }
        });
    }

    public void updateTask(String id, TaskModel newTask) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tasks");
        query.getInBackground(id, (task, e) -> {
            if (e == null) {
                task.put("title", newTask.getTitle());
                task.put("description", newTask.getDescription());
                task.put("completed", newTask.getCompleted());
                task.saveInBackground();
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
