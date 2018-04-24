package com.williamgrishko.recordmanager;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

    public AppDatabase recordDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        if (recordDatabase == null) {
            recordDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "records.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuViewAll:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuAddRecord:
                intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
