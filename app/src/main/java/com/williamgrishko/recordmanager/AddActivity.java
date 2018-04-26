package com.williamgrishko.recordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Date;

public class AddActivity extends BaseActivity {

    EditText edtName;
    EditText edtDescription;
    EditText edtPrice;
    RatingBar rtbRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edtName = findViewById(R.id.edtName);
        edtDescription = findViewById(R.id.edtDescription);
        edtPrice = findViewById(R.id.edtPrice);
        rtbRating = findViewById(R.id.rtbRating);
    }

    public void addRecordOnClick(View view) {

        final String name = edtName.getText().toString();

        if( name.trim().equals("")) {
            edtName.setError(getString(R.string.name_required));
            return;
        }

        final String description = edtDescription.getText().toString();

        final String priceStr = edtPrice.getText().toString();
        final BigDecimal price = priceStr.trim().equals("") ? new BigDecimal("0") : new BigDecimal(priceStr);

        final int rating = (int) rtbRating.getRating();
        final Date createdDate = new Date();
        final Date modifiedDate = new Date();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Record record = new Record();
                record.setName(name);
                record.setDescription(description);
                record.setPrice(price);
                record.setRating(rating);
                record.setDateCreated(createdDate);
                record.setDateModified(modifiedDate);

                recordDatabase.recordDao().addRecord(record);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }).start();

    }
}
