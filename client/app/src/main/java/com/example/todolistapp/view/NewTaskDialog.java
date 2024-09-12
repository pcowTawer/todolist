package com.example.todolistapp.view;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.todolistapp.R;
import com.example.todolistapp.model.TaskModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class NewTaskDialog extends BottomSheetDialogFragment {
    private EditText newTaskTitleText;
    private EditText newTaskDescriptionText;
    private Button newTaskSaveButton;

    public static final String TAG = "NewTaskDialog";
    public static NewTaskDialog getInstance() {
        return new NewTaskDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_task_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newTaskTitleText = requireView().findViewById(R.id.newTaskTitle);
        newTaskDescriptionText = requireView().findViewById(R.id.newTaskDescription);
        newTaskSaveButton = requireView().findViewById(R.id.newTaskSaveButton);
        newTaskSaveButton.setEnabled(false);
        newTaskSaveButton.setTextColor(Color.GRAY);

        boolean isUpdate;
        final Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true;
            newTaskTitleText.setText(bundle.getString("title"));
            newTaskDescriptionText.setText(bundle.getString("description"));
            newTaskSaveButton.setEnabled(true);
            newTaskSaveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_700));
        } else {
            isUpdate = false;
        }

        newTaskTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    newTaskSaveButton.setEnabled(false);
                    newTaskSaveButton.setTextColor(Color.GRAY);
                }
                else {
                    newTaskSaveButton.setEnabled(true);
                    newTaskSaveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_700));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        newTaskSaveButton.setOnClickListener(v -> {
            TaskModel task = new TaskModel();
            task.setTitle(newTaskTitleText.getText().toString());
            task.setDescription(newTaskDescriptionText.getText().toString());
            task.setCompleted(false);
            NewTaskDialogListener activity = (NewTaskDialogListener) getActivity();
            assert activity != null;
            if (isUpdate) {
                activity.handleUpdateTask(bundle.getString("id"), task);
            } else {
                activity.handleAddTask(task);
            }
            dismiss();
        });
    }
}
