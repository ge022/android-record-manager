package com.williamgrishko.recordmanager;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface RecordDao {

    @Query("SELECT name FROM records")
    LiveData<List<String>> getNames();

    @Query("SELECT * FROM RECORDS WHERE recordID LIKE :recordID")
    LiveData<Record> getRecord(int recordID);

    @Insert
    void addRecord(Record record);

    @Update
    void updateRecord(Record record);

    @Delete
    void deleteRecord(Record record);

}
