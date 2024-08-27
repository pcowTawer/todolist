package com.example.todolistapp.retrofit;

import com.example.todolistapp.model.TaskModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("tasks")
    Call<ArrayList<TaskModel>> getTasks();

    @POST("tasks")
    Call<Void> addTask(@Body TaskModel task);

    @PUT("tasks/{id}")
    Call<Void> updateTask(@Path("id") int id, @Body TaskModel task);

    @DELETE("tasks/{id}")
    Call<Void> deleteTask(@Path("id") int id);
}
