package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.todolistapp.Adapter.ToDoAdapter;
import com.example.todolistapp.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    interface RequestTasks {
        @GET("tasks")
        Call<List<ToDoModel>> getTasks();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<ToDoModel> tasks = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ToDoAdapter taskAdapter = new ToDoAdapter(this);
        tasksRecyclerView.setAdapter(taskAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestTasks requestTasks = retrofit.create(RequestTasks.class);
        requestTasks.getTasks().enqueue(new Callback<List<ToDoModel>>() {
            @Override
            public void onResponse(Call<List<ToDoModel>> call, Response<List<ToDoModel>> response) {
                assert response.body() != null;
                tasks.addAll(response.body());
                taskAdapter.setTasks(tasks);
            }

            @Override
            public void onFailure(Call<List<ToDoModel>> call, Throwable throwable) {
                Log.d("My Logs", "Cringe!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + throwable.getMessage());
            }
        });
    }
}