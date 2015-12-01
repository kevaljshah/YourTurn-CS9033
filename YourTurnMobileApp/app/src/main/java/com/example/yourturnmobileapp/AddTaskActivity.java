package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.yourturnmobileapp.R;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import helper.TaskDatabaseHelper;
import models.Task;

/**
 * Created by Apoorva Walimbe on 10/31/2015.
 */
public class AddTaskActivity extends Activity {

    private static final String TAG = "AddTaskActivity";
    private static final String url = "jdbc:mysql://10.0.2.2:3306/yourturndb";
    private static final String user = "root";
    private static final String pass = "";

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

        EditText name = (EditText) findViewById(R.id.taskName1);
        String tName = name.getText().toString();
        task.setTaskName(tName);

        EditText desc = (EditText) findViewById(R.id.taskDesc1);
        String tDesc = desc.getText().toString();
        task.setTaskDesc(tDesc);

        EditText assignee = (EditText) findViewById(R.id.assignee1);
        String tAssignee = assignee.getText().toString();
        task.setAssignee(tAssignee);

        EditText dueDate = (EditText) findViewById(R.id.dueDate1);
        String tDueDate = dueDate.getText().toString();
        task.setDueDate(tDueDate);

        return task;
    }

    public void saveTask(Task task) {
        //code to persist the data into database
        /*Connection con = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection successful");
            stmt = con.createStatement();
            int results = stmt.executeUpdate("INSERT INTO tasks VALUES ("+task.getTaskName()+","+task.getTaskDesc()+
                    ","+task.getDueDate()+",null,"+task.getAssignee()+",0,0)");
            if (results == 1) {
                Toast.makeText(getBaseContext(), "Task added successfully", Toast.LENGTH_LONG).show();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try{
                stmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/


        Log.d("Saving Trip", task.toString());
        Intent intent = new Intent();
        intent.putExtra("TaskData", task);
        setResult(RESULT_OK, intent);
        TaskDatabaseHelper databaseHelper = new TaskDatabaseHelper(getBaseContext());
        long taskID = databaseHelper.insertTask(task);
        Toast.makeText(getBaseContext(), "Task created successfully.", Toast.LENGTH_LONG).show();
        Log.d("Task created with id: ", Long.toString(taskID));
        //finish();
        Intent intent1 = new Intent(getBaseContext(), ViewTaskActivity.class);
        startActivity(intent1);

    }

    public void cancelTask() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }


}

