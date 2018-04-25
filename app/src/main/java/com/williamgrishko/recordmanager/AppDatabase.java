package com.williamgrishko.recordmanager;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Record.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecordDao recordDao();

}
