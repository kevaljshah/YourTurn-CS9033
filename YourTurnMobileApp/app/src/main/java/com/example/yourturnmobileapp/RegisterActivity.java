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
    EditText editText3,editText4,editText5,editText6,editText7,editText8,editGroup;

    private static final String TAG = "RegisterActivity";
    private static final String url = "jdbc:mysql://10.0.2.2:3306/yourturndb";
    private static final String user = "root";
    private static final String pass = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText3 = (EditText)findViewById(R.id.username);
        editText4 = (EditText)findViewById(R.id.useremail) ;
        editText5 = (EditText)findViewById(R.id.userphno);
        editText6 = (EditText)findViewById(R.id.editText6);
        editText7 = (EditText)findViewById(R.id.editText7);
        editText8 = (EditText)findViewById(R.id.userbdate);
        editGroup = (EditText) findViewById(R.id.groupname);
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
                break;
        }
    }

    public User registerUser() {
        User user = new User();

        EditText username = (EditText) findViewById(R.id.username);
        String uName = username.getText().toString();
        user.setUserName(uName);

        EditText userphno = (EditText) findViewById(R.id.userphno);
        String uPhNo = userphno.getText().toString();
        user.setUserNumber(uPhNo);

        EditText uemail = (EditText) findViewById(R.id.useremail);
        String uEmail = uemail.getText().toString();
        user.setUserEmail(uEmail);

        EditText userBDate = (EditText) findViewById(R.id.userbdate);
        String uBDate = userBDate.getText().toString();
        user.setUserBDate(uBDate);

        EditText userGroup = (EditText) findViewById(R.id.groupname);
        String roomieGroup = userGroup.getText().toString();
        user.setUserGroup(roomieGroup);
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
        Toast.makeText(getBaseContext(), "User Registered successfully.", Toast.LENGTH_LONG).show();
        Log.d("USer created with id: ", Long.toString(taskID));
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
