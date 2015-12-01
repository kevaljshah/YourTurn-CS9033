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

import models.User;

/**
 * Created by Keval on 02-11-2015.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    ImageButton imageButton4;
    EditText editText3,editText4,editText5,editText6,editText7,editText8;

    private static final String TAG = "RegisterActivity";
    private static final String url = "jdbc:mysql://10.0.2.2:3306/yourturndb";
    private static final String user = "root";
    private static final String pass = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4) ;
        editText5 = (EditText)findViewById(R.id.editText5);
        editText6 = (EditText)findViewById(R.id.editText6);
        editText7 = (EditText)findViewById(R.id.editText7);
        editText8 = (EditText)findViewById(R.id.editText8);
        imageButton4 = (ImageButton)findViewById(R.id.imageButton4);


        imageButton4.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.imageButton4:

                User user = registerUser();
                saveUser(user);
                startActivity(new Intent(this,MainActivity.class));

                break;
        }
    }

    public User registerUser() {
        User user = new User();

        EditText name = (EditText) findViewById(R.id.taskName1);
        String uName = name.getText().toString();
        user.setUserName(uName);

        EditText desc = (EditText) findViewById(R.id.taskDesc1);
        String uPhNo = desc.getText().toString();
        user.setUserNumber(uPhNo);

        EditText assignee = (EditText) findViewById(R.id.assignee1);
        String uEmail = assignee.getText().toString();
        user.setUserEmail(uEmail);

        EditText dueDate = (EditText) findViewById(R.id.dueDate1);
        String uBDate = dueDate.getText().toString();
        user.setUserBDate(uBDate);

        return user;
    }

    public void saveUser(User user) {
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


        Log.d("Saving Trip", user.toString());
        Intent intent = new Intent();
        intent.putExtra("TaskData", user);
        setResult(RESULT_OK, intent);
        RegisterDatabaseHelper databaseHelper = new RegisterDatabaseHelper(getBaseContext());
        long taskID = databaseHelper.insertUser(user);
        Toast.makeText(getBaseContext(), "Task created successfully.", Toast.LENGTH_LONG).show();
        Log.d("Task created with id: ", Long.toString(taskID));
        //finish();
        Intent intent1 = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent1);

    }

    public void cancelUser() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }


}
