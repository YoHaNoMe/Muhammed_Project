package com.example.testapplicationmuhammed;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Medicine.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MedicineDao medicineDao();
}
