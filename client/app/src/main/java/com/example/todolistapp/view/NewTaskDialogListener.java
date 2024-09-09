package com.example.todolistapp.view;

import com.example.todolistapp.model.TaskModel;

public interface NewTaskDialogListener {
    void handleUpdateTask(String id, TaskModel task);
    void handleAddTask(TaskModel task);
    void handleNewTaskDialogDismiss();
}
