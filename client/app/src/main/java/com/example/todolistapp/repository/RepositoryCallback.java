package com.example.todolistapp.repository;

import java.util.List;
import com.example.todolistapp.model.TaskModel;

public interface RepositoryCallback {
    void onComplete(List<TaskModel> taskList);
}
