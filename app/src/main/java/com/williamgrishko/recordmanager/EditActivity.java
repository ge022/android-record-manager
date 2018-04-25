package com.williamgrishko.recordmanager;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Date;

public class EditActivity extends BaseActivity {

    int position;
    LiveData<Record> record;
    EditText edtName;
    EditText edtDescription;
    EditText edtPrice;
    RatingBar rtbRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtName = findViewById(R.id.edtName);
        edtDescription = findViewById(R.id.edtDescription);
        edtPrice = findViewById(R.id.edtPrice);
        rtbRating = findViewById(R.id.rtbRating);

        // Get intent data
        Intent intent = getIntent();
        position = intent.getIntExtra("POSITION", 0);

        record = recordDatabase.recordDao().getRecord(position);
        record.observe(this, new Observer<Record>() {
            @Override
            public void onChanged(@Nullable Record record) {
                if (record == null) {
                    return;
                }

                edtName.setText(record.getName());
                edtDescription.setText(record.getDescription());
                edtPrice.setText(record.getPrice().toString());
                rtbRating.setRating(record.getRating());

            }
        });


    }

    public void saveRecordOnClick(View view) {
        final String name = edtName.getText().toString();

        if( name.trim().equals("")) {
            edtName.setError("Name is required.");
            return;
        }

        final String description = edtDescription.getText().toString();

        final String priceStr = edtPrice.getText().toString();
        final BigDecimal price = priceStr.trim().equals("") ? new BigDecimal("0") : new BigDecimal(priceStr);

        final int rating = (int) rtbRating.getRating();

        final Date modifiedDate = new Date();

        new Thread(new Runnable() {
            @Override
            public void run() {

                record.getValue().setName(name);
                record.getValue().setDescription(description);
                record.getValue().setPrice(price);
                record.getValue().setRating(rating);
                record.getValue().setDateModified(modifiedDate);

                recordDatabase.recordDao().updateRecord(record.getValue());
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                startActivity(intent);
            }
        }).start();
    }
}
