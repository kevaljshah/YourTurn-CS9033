package models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by appur_000 on 10/31/2015.
 */
public class Task implements Parcelable {

    private long taskId;
    private String taskName;
    private String taskDesc;
    private String dueDate;
    private String assignee;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }


    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel p) {
            return new Task(p);
        }

        public Task[] newArray(int size) { return new Task[size]; }
    };

    public Task(Parcel p) {

        Log.d("Parcel object: ", p.toString());
        this.taskId = p.readLong();
        this.taskName = p.readString();
        this.taskDesc = p.readString();
        this.dueDate = p.readString();
        this.assignee = p.readString();
    }

    public Task(long taskId, String taskName, String taskDesc, String dueDate, String assignee) {

    }

    public Task() {
    }

    /*public Person(String name, int personID, String phoneNum, String currentLocation, String destination, String eta) {

        Log.d("Person object: ", name);
        this.name = name;
        this.personID = personID;
        this.phoneNum = phoneNum;
        this.currentLocation = currentLocation;
        this.destination = destination;
        this.eta = eta;
        // TODO - fill in here, please note you must have more arguments here
    }*/

    /**
     * Serialize Person object by using writeToParcel.
     * This function is automatically called by the
     * system when the object is serialized.
     *
     * @param dest Parcel object that gets written on
     * serialization. Use functions to write out the
     * object stored via your member variables.
     *
     * @param flags Additional flags about how the object
     * should be written. May be 0 or PARCELABLE_WRITE_RETURN_VALUE.
     * In our case, you should be just passing 0.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public int describeContents() {
        return 0;
    }
}
