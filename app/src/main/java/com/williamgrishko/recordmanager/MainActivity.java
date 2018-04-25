package com.williamgrishko.recordmanager;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends BaseActivity {

    ListView listViewRecords;
    LiveData<List<Record>> items;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewRecords = findViewById(R.id.listViewRecords);

        items = recordDatabase.recordDao().getAll();
        items.observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable List<Record> records) {
                adapter = new ArrayAdapter<Record>(getApplicationContext(), R.layout.activity_listview, records);
                listViewRecords.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                listViewRecords.invalidateViews();
                listViewRecords.refreshDrawableState();
            }
        });

        listViewRecords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                intent.putExtra("RECORD", (int) items.getValue().get(position).getRecordID());
                startActivity(intent);
            }
        });
    }


    public void newRecordOnClick(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}
