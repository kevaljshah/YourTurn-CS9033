package com.example.yourturnmobileapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.yourturnmobileapp.R;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import models.Task;

/**
 * Created by Apoorva Walimbe on 10/31/2015.
 */
public class AddTaskActivity extends Activity {

    private static final String TAG = "AddTaskActivity";
    private static final String url = "jdbc:mysql://localhost:3306/yourturndb";
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
        task.setTaskName(findViewById(R.id.taskName1).toString());
        task.setTaskDesc(findViewById(R.id.taskDesc1).toString());
        task.setAssignee(findViewById(R.id.assignee1).toString());
        task.setDueDate(findViewById(R.id.dueDate1).toString());
        return task;
    }

    public void saveTask(Task task) {
        //code to persist the data into database
        Connection con = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.driver");
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
        }
    }

    public void cancelTask() {

    }


}

