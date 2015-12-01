package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import models.Task;

/**
 * Created by appur_000 on 11/30/2015.
 */
public class TaskDatabaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "YOURTURN_DBASE";

    private static final String TASK_DATABASE_NAME = "TASKS";
    public static final String COLUMN_TASK_ID = "_id";
    public static final String COLUMN_TASK_NAME = "tname";
    public static final String COLUMN_TASK_DESC = "tdesc";
    public static final String COLUMN_TASK_DATE = "tdate";
    public static final String COLUMN_TASK_ASSIGNEE = "tassignee";
    public static final String COLUMN_TASK_REMINDER = "treminder";
    public static final String COLUMN_TASK_STATUS = "tstatus";
    private static final String TASK_DATABASE_CREATE =
            "CREATE TABLE " + TASK_DATABASE_NAME + " ( " +
                    COLUMN_TASK_ID + " integer primary key autoincrement, " +
                    COLUMN_TASK_NAME + " varchar(25), " +
                    COLUMN_TASK_DESC + " text, " +
                    COLUMN_TASK_DATE + " date, " +
                    COLUMN_TASK_ASSIGNEE + " varchar(25), " +
                    COLUMN_TASK_REMINDER + " boolean, " +
                    COLUMN_TASK_STATUS + " boolean" + ")";

    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS ";

    private static final String FETCH_TASKS_QUERY = "SELECT * FROM " + TASK_DATABASE_NAME;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TASK_DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY+TASK_DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

    public TaskDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public long insertTask(Task task) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_TASK_NAME, task.getTaskName());
        Log.d("Task Name: ", task.getTaskName());
        values.put(COLUMN_TASK_DESC, task.getTaskDesc());
        Log.d("Task desc: ", task.getTaskDesc());
        values.put(COLUMN_TASK_DATE, task.getDueDate());
        values.put(COLUMN_TASK_ASSIGNEE, task.getAssignee());
        values.put(COLUMN_TASK_REMINDER, false);
        values.put(COLUMN_TASK_STATUS, false);

        return getWritableDatabase().insert(TASK_DATABASE_NAME, null, values);
    }

    public Cursor fetchTasks() {
        Cursor cursor = getReadableDatabase().rawQuery(FETCH_TASKS_QUERY, null);
        return cursor;
    }

}
