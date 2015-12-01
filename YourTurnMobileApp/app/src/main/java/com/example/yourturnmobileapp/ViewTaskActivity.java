package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import helper.TaskDatabaseHelper;
import models.Task;

/**
 * Created by appur_000 on 11/30/2015.
 */
public class ViewTaskActivity extends Activity {

    private static final String TAG = "ViewTaskActivity";
    Task task = new Task();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        viewTasks();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void viewTasks() {
        TaskDatabaseHelper databaseHelper = new TaskDatabaseHelper(getBaseContext());
        Cursor cursor = databaseHelper.fetchTasks();

        if(!cursor.moveToFirst()) {
            Toast.makeText(getBaseContext(), "No Tasks Created", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        } else {
            String[] columns = new String[] {
                    TaskDatabaseHelper.COLUMN_TASK_NAME,
                    TaskDatabaseHelper.COLUMN_TASK_ASSIGNEE
            };

            //XML views to be bound to the data
            int[] to = new int[] {
                    R.id.viewTaskName,
                    R.id.viewTaskAssignee
            };

            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                    this, R.layout.activity_view_task,
                    cursor, columns, to, 0);

            final ListView listView = (ListView) findViewById(R.id.viewTasks);
            listView.setAdapter(simpleCursorAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                    String taskName = cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_TASK_NAME));
                    task.setTaskName(taskName);
                    task.setTaskId(cursor.getInt(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_TASK_ID)));
                    task.setTaskDesc(cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_TASK_DESC)));
                    task.setDueDate(cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_TASK_DATE)));
                    task.setAssignee(cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_TASK_ASSIGNEE)));

                    Log.d("Name: ", taskName);
                    Log.d("Desc: ", task.getTaskDesc());
                    Intent intent = new Intent(getBaseContext(), ViewTaskDetailsActivity.class);
                    intent.putExtra("TaskObject", task);
                    intent.putExtra("TaskName", taskName);
                    startActivity(intent);
                }
            });

        }
    }
}
