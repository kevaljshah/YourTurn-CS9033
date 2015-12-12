package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import models.Task;

/**
 * Created by Apoorva Walimbe on 11/30/2015.
 */
public class ViewTaskDetailsActivity extends Activity {

    private static final String TAG = "ViewTaskDetailsActivity";
    private Intent newIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_taskdetails);
        // TODO - fill in here
        //onNewIntent(getIntent());
        Task task = getIntent().getExtras().getParcelable("TaskObject");
        //Log.d("detail name",task.getTaskName());
        //Log.d("detail desc", task.getTaskDesc());
        //Task task = getIntent().getExtras().
        viewTask(task);
    }

    public Task getTask(Intent i) {
        Task task = i.getParcelableExtra("TaskObject");
        return task;
    }

    @Override
    public void onNewIntent(Intent intent) {
        newIntent = intent;
    }



    public void viewTask(Task task) {

        // TODO - fill in here
        TextView id = (TextView) findViewById(R.id.viewTaskId);
        id.setText(Long.toString(task.getTaskId()));

        TextView name = (TextView) findViewById(R.id.viewTaskName);
        name.setText(task.getTaskName());

        TextView desc = (TextView) findViewById(R.id.viewTaskDesc);
        desc.setText(task.getTaskDesc());

        TextView dueDate = (TextView) findViewById(R.id.viewDueDate);
        dueDate.setText(task.getDueDate());

        TextView assignee = (TextView) findViewById(R.id.viewAssignee);
        assignee.setText(task.getAssignee());
    }
}
