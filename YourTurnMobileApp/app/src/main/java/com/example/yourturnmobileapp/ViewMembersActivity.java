package com.example.yourturnmobileapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import helper.CustomListAdapter;
import helper.CustomMemberListAdapter;
import helper.TaskDatabaseHelper;
import models.Task;
import models.User;

/**
 * Created by Apoorva on 12/13/2015.
 */
public class ViewMembersActivity extends Activity {

    private static final String TAG = "ViewMembersActivity";
    Context context;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_members);

        context=this;
        final ArrayList<User> members = viewMembers();
        CustomMemberListAdapter adapter=new CustomMemberListAdapter(this, members);
        listView=(ListView)findViewById(R.id.membersListView);
        listView.setAdapter(adapter);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String member = members.get(+position).getUserName();
                Toast.makeText(getApplicationContext(), member, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private ArrayList<User> viewMembers() {
        ArrayList<User> users = new ArrayList<User>();
        TaskDatabaseHelper databaseHelper = new TaskDatabaseHelper(getBaseContext());
        users = databaseHelper.fetchMembers();
        return users;
    }

}
