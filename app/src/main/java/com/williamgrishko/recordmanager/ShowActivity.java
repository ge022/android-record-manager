package com.williamgrishko.recordmanager;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ShowActivity extends BaseActivity {

    int recordID;
    LiveData<Record> record;
    TextView txtName;
    TextView txtDescription;
    TextView txtPrice;
    RatingBar rtbRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);
        rtbRating = findViewById(R.id.rtbRating);

        // Get intent data
        Intent intent = getIntent();
        recordID = intent.getIntExtra("RECORD", 0);

        record = recordDatabase.recordDao().getRecord(recordID);
        record.observe(this, new Observer<Record>() {
            @Override
            public void onChanged(@Nullable Record record) {
                if (record == null) {
                    return;
                }

                txtName.setText(record.getName());
                txtDescription.setText(record.getDescription());
                txtPrice.setText(record.getPrice().toString());
                rtbRating.setRating(record.getRating());

            }
        });

    }

    public void btnDeleteOnClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to delete this record?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Delete record
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                recordDatabase.recordDao().deleteRecord(record.getValue());
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }).start();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .create().show();
    }

    public void btnEditOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra("RECORD", recordID);
        startActivity(intent);
    }
}
