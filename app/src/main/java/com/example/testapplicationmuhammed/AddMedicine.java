package com.example.testapplicationmuhammed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMedicine extends AppCompatActivity {

    String medicineName;
    String medicineQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        Button addMedicineButton = findViewById(R.id.add_medicine_btn);
        final EditText medicineNameEditText = findViewById(R.id.medicine_name_editText);
        final EditText medicineQuantityEditText = findViewById(R.id.medicine_quantity_editText);

        addMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineName = medicineNameEditText.getText().toString();
                medicineQuantity = medicineQuantityEditText.getText().toString();
                SaveMedicine saveMedicine = new SaveMedicine();
                String[] params = {medicineName, medicineQuantity};
                saveMedicine.execute(params);
            }
        });
    }

    class SaveMedicine extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            Medicine medicine = new Medicine();
            medicine.setMedicineName(strings[0]);
            medicine.setQuantity(strings[1]);

            // Insert new medicine
            DatabaseClient.getInstance(getApplicationContext())
                    .getAppDatabase()
                    .medicineDao()
                    .insert(medicine);

            // Return void
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(AddMedicine.this, MedicalWareHouse.class));
        }
    }
}