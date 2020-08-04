package com.example.testapplicationmuhammed;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Medicine {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name= "medicine_name")
    public String medicineName;

    @ColumnInfo(name = "quantity")
    public String quantity;

    public void setId(int id) {
        this.id = id;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getQuantity() {
        return quantity;
    }
}
