package com.example.todolistapp.view;

import com.example.todolistapp.model.TaskModel;

public interface NewTaskDialogListener {
    void handleUpdateTask(int id, TaskModel task);
    void handleAddTask(TaskModel task);
    void handleNewTaskDialogDismiss();
}
