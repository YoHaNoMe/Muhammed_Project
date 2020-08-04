package com.example.testapplicationmuhammed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MedicalWareHouse extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Test test = new Test();
//        test.execute();

        recyclerView = findViewById(R.id.recyclerview);
        floatingActionButton = findViewById(R.id.floatingActionButton);


//        recyclerView.setAdapter(new MyAdapter(Collections.<Medicine>emptyList()));
        recyclerView.setHasFixedSize(true);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicalWareHouse.this, AddMedicine.class);
                startActivity(intent);
            }
        });
        GetMedicine getMedicine = new GetMedicine();
        getMedicine.execute();
    }

    class GetMedicine extends AsyncTask<Void, Void, List<Medicine>>{

        @Override
        protected List<Medicine> doInBackground(Void... voids) {
            // Get all medicines
            List<Medicine> medicines = DatabaseClient.getInstance(getApplicationContext())
                    .getAppDatabase()
                    .medicineDao()
                    .getAll();
            for (Medicine m: medicines) {
                Log.d("MainActivity", m.getMedicineName());
            }
            return medicines;
        }

        @Override
        protected void onPostExecute(List<Medicine> medicines) {
            super.onPostExecute(medicines);
            recyclerView.setAdapter(new MyAdapter(getApplicationContext(), medicines));
        }
    }

    class Test extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            // Insert new medicine
            DatabaseClient.getInstance(getApplicationContext())
                    .getAppDatabase()
                    .medicineDao()
                    .dropDatabase();

            return null;
        }
    }
}