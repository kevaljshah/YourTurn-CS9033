package com.example.yourturnmobileapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.yourturnmobileapp.R;

import java.security.PublicKey;

import models.Task;

/**
 * Created by Apoorva Walimbe on 10/31/2015.
 */
public class AddTaskActivity extends Activity {

    private static final String TAG = "AddTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        final ImageButton createTask1 = (ImageButton) findViewById(R.id.createTask1);
        createTask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = createTask();
                saveTask(task);
            }
        });

        final ImageButton canTask = (ImageButton) findViewById(R.id.canTask);
        canTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTask();
            }
        });
    }

    public Task createTask() {
        Task task = new Task();
        task.setTaskName(findViewById(R.id.taskName).toString());
        task.setTaskDesc(findViewById(R.id.taskDesc).toString());
        task.setAssignee(findViewById(R.id.assignee).toString());
        task.setDueDate(findViewById(R.id.dueDate).toString());
        return task;
    }

    public void saveTask(Task task) {
        //code to persist the data into database
    }

    public void cancelTask() {

    }


}
