package com.example.testapplicationmuhammed;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MedicineDao {
    @Query("SELECT * FROM medicine")
    List<Medicine> getAll();

    @Insert
    void insert(Medicine medicine);

    @Delete
    void delete(Medicine medicine);

    @Update
    void update(Medicine medicine);

    @Query("DELETE FROM medicine")
    void dropDatabase();
}
