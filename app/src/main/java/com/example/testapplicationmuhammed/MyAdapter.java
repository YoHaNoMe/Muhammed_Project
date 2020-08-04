package com.example.testapplicationmuhammed;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Medicine> medicines;
    private Context context;

    public MyAdapter(Context context, List<Medicine> medicines){
        this.medicines = medicines;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_view_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(medicines.size() >=1) {
            holder.textView.setText(medicines.get(position).getMedicineName());
            holder.quantityTextView.setText(medicines.get(position).getQuantity());
//        holder.textView.setText("Item; " + position);
        }
    }


    @Override
    public int getItemCount() {
        if(medicines.size() >=1)
            return medicines.size();
        else
            return 100;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView quantityTextView;
        Button addQuantity;
        Button removeQuantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
            quantityTextView = itemView.findViewById(R.id.item_quantity_text);
            addQuantity = itemView.findViewById(R.id.add_quantity_btn);
            removeQuantity = itemView.findViewById(R.id.remove_quantity_btn);




            addQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Medicine medicine = medicines.get(getLayoutPosition());
                    Log.d("MyAdapter", medicine.getMedicineName());
                    Log.d("MyAdapter", medicine.getQuantity());
                    AddQuantity addQuantityListener = new AddQuantity();
                    addQuantityListener.execute(medicine);
                }
            });

            removeQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Medicine medicine = medicines.get(getLayoutPosition());
                    Log.d("MyAdapter", medicine.getMedicineName());
                    Log.d("MyAdapter", medicine.getQuantity());
                    RemoveQuantity removeQuantityListener = new RemoveQuantity();
                    removeQuantityListener.execute(medicine);
                }
            });
        }
    }

    class AddQuantity extends AsyncTask<Medicine, Void, Void>{

        @Override
        protected Void doInBackground(Medicine... medicines) {

//            Medicine medicine = new Medicine();
            int quantity = Integer.parseInt(medicines[0].getQuantity());
            quantity+=1;
//            medicine.setMedicineName(medicines[0].getMedicineName());
            medicines[0].setQuantity(String.valueOf(quantity));

            // Update medicine
            DatabaseClient.getInstance(context)
                    .getAppDatabase()
                    .medicineDao()
                    .update(medicines[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            notifyDataSetChanged();
        }
    }

    class RemoveQuantity extends AsyncTask<Medicine, Void, Void>{

        @Override
        protected Void doInBackground(Medicine... medicines) {

//            Medicine medicine = new Medicine();
            int quantity = Integer.parseInt(medicines[0].getQuantity());
            quantity-=1;
//            medicine.setMedicineName(medicines[0].getMedicineName());
            medicines[0].setQuantity(String.valueOf(quantity));

            // Update medicine
            DatabaseClient.getInstance(context)
                    .getAppDatabase()
                    .medicineDao()
                    .update(medicines[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            notifyDataSetChanged();
        }
    }
}
