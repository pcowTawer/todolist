package com.example.todolistapp.repository;

import android.util.Log;


import com.example.todolistapp.model.TaskModel;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TaskRepository {
    private static TaskRepository taskRepository;

    public static TaskRepository getInstance(){
        if (taskRepository == null){
            taskRepository = new TaskRepository();
        }
        return taskRepository;
    }

    public void getTasks(RepositoryCallback callback) {
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
            callback.onComplete(taskList);
        });
    }

    public void addTask(TaskModel task, RepositoryCallback callback) {
        ParseObject taskParseObject = new ParseObject("Tasks");
        taskParseObject.put("title", task.getTitle());
        taskParseObject.put("description", task.getDescription());
        taskParseObject.put("completed", task.getCompleted());
        taskParseObject.saveInBackground(e -> {
            if (e == null) {
                getTasks(callback);
            } else {
                Log.d("Response failure", Objects.requireNonNull(e.getLocalizedMessage()));
            }
        });

    }

    public void updateTask(String id, TaskModel newTask, RepositoryCallback callback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tasks");
        query.getInBackground(id, (task, e) -> {
            if (e == null) {
                task.put("title", newTask.getTitle());
                task.put("description", newTask.getDescription());
                task.put("completed", newTask.getCompleted());
                task.saveInBackground(e1 -> {
                    if (e1 == null) getTasks(callback);
                });
            }
        });
    }

    public void deleteTask(String id) {

    }
}
