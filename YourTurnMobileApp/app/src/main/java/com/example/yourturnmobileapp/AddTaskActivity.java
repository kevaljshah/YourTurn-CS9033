package com.example.yourturnmobileapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import helper.AlarmReceiver;
import helper.TaskDatabaseHelper;
import helper.TimePickerFragment;
import models.Task;

/**
 * Created by Apoorva Walimbe on 10/31/2015.
 */
public class AddTaskActivity extends Activity {

    private static final String TAG = "AddTaskActivity";
    private static final String url = "jdbc:mysql://10.0.2.2:3306/yourturndb";
    private static final String user = "root";
    private static final String pass = "";
    EditText dueDate;
    Calendar tripCalendarDate;
    private Spinner assignee;
    EditText taskName;
    EditText taskDesc;
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private EditText alarmTimePicker;
    TimePicker myTimePicker;
    Button setReminder;
    TimePickerDialog timePickerDialog;
    final static int RQS_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskName = (EditText) findViewById(R.id.taskName);
        taskDesc = (EditText) findViewById(R.id.taskDesc);
        alarmTimePicker = (EditText) findViewById(R.id.alarmTimePicker);
        //alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        setReminder = (Button) findViewById(R.id.setReminder);

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

        dueDate = (EditText) findViewById(R.id.dueDate);
        dueDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {

                    Calendar currentDate = Calendar.getInstance();
                    int currentYear = currentDate.get(Calendar.YEAR);
                    int currentMonth = currentDate.get(Calendar.MONTH);
                    int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker = new DatePickerDialog(AddTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                            tripCalendarDate = Calendar.getInstance();

                            tripCalendarDate.set(selectedyear, selectedmonth, selectedday);
                            SimpleDateFormat format1 = new SimpleDateFormat("MMM dd, yyyy");
                            dueDate.setText(format1.format(tripCalendarDate.getTime()));
                            dueDate.clearFocus();
                        }
                    }, currentYear, currentMonth, currentDay);

                    mDatePicker.setTitle("Select Due Date");
                    mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    mDatePicker.show();
                }
            }
        });

        /*alarmTimePicker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(),"TimePicker");
                }
            }
        });*/

        setReminder.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                setReminder.setVisibility(View.INVISIBLE);
                alarmTimePicker.setVisibility(View.VISIBLE);
                openTimePickerDialog(false);

            }});

        assignee = (Spinner) findViewById(R.id.assignee);
        List<String> assignees = new ArrayList<String>();
        assignees.add("--Select--");
        assignees.add("Apoorva");
        assignees.add("Shweta");
        assignees.add("Saudamini");
        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,assignees);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignee.setAdapter(dataAdapter);
    }

    private void openTimePickerDialog(boolean is24r){
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(
                AddTaskActivity.this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24r);
        timePickerDialog.setTitle("Set Alarm Time");
        timePickerDialog.show();

    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener
            = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if(calSet.compareTo(calNow) <= 0){
                //Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
            }
            setAlarm(calSet);
        }
    };

    private void setAlarm(Calendar targetCal){
        alarmTimePicker.setText(targetCal.getTime().getHours() + ":" + targetCal.getTime().getMinutes());
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }

    public Task createTask() {
        Task task = new Task();
        task.setTaskName(taskName.getText().toString());
        task.setTaskDesc(taskDesc.getText().toString());
        task.setAssignee(String.valueOf(assignee.getSelectedItem()));
        task.setDueDate(dueDate.getText().toString());
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

        if(taskName.getText().toString().equals("") || taskDesc.getText().toString().equals("")
                || dueDate.getText().toString().equals("")
                || String.valueOf(assignee.getSelectedItem()).matches("--Select--")) {
            Toast.makeText(getBaseContext(), "Enter every field to create a new task", Toast.LENGTH_LONG).show();
        } else {
            Log.d("Saving Trip", task.toString());
            Intent intent = new Intent();
            intent.putExtra("TaskData", task);
            setResult(RESULT_OK, intent);
            TaskDatabaseHelper databaseHelper = new TaskDatabaseHelper(getBaseContext());
            long taskID = databaseHelper.insertTask(task);
            Toast.makeText(getBaseContext(), "Task created successfully.", Toast.LENGTH_LONG).show();
            Log.d("Task created with id: ", Long.toString(taskID));
            Intent viewTaskIntent = new Intent(getBaseContext(), ViewTaskActivity.class);
            startActivity(viewTaskIntent);
        }
    }

    public void cancelTask() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }


}

