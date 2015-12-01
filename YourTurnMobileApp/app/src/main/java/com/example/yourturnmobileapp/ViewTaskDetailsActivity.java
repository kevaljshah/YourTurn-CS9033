package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import models.Task;

/**
 * Created by appur_000 on 11/30/2015.
 */
public class ViewTaskDetailsActivity extends Activity {

    private static final String TAG = "ViewTaskDetailsActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_taskdetails);
        // TODO - fill in here

        Task task = getTask(getIntent());
        //Log.d("detail name",task.getTaskName());
        //Log.d("detail desc", task.getTaskDesc());
        viewTask(task);
    }

    public Task getTask(Intent i) {
        Task task = i.getParcelableExtra("TaskObject");
        return task;
    }

    public void viewTask(Task task) {

        // TODO - fill in here
        TextView id = (TextView) findViewById(R.id.viewTaskId);
        id.setText(Long.toString(task.getTaskId()));

        TextView name = (TextView) findViewById(R.id.viewTaskName);
        name.setText(task.getTaskName());

        TextView desc = (TextView) findViewById(R.id.viewTaskDesc);
        desc.setText(task.getTaskDesc());

    }
}
