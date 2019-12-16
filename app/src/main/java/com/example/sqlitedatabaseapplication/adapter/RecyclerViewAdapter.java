package com.example.sqlitedatabaseapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedatabaseapplication.R;
import com.example.sqlitedatabaseapplication.model.Receipt;


import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Receipt> receiptsList;
    private ReceiptDelegate receiptDelegate;


    public interface ReceiptDelegate{
        void deleteItem(int position);
        void viewItem(Receipt receipt);
    }

    public RecyclerViewAdapter(List<Receipt> receiptsList, ReceiptDelegate receiptDelegate) {
        this.receiptsList = receiptsList;
        this.receiptDelegate = receiptDelegate;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipts_recycler_view_layout,parent,false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, final int position) {
        final Receipt receipt = receiptsList.get(position);
        //holder.idTextView.setText(""+receipt.getId());
        holder.titleTextView.setText(receipt.getTitle());
        holder.priceTextView.setText(receipt.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receiptDelegate.viewItem(receipt);
            }
        });
    }

    @Override
    public int getItemCount() {
        return receiptsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //TextView idTextView;
        TextView titleTextView;
        TextView priceTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //idTextView = itemView.findViewById(R.id.receiptsIDTextViewRVL);
            titleTextView = itemView.findViewById(R.id.title_textview);
            priceTextView = itemView.findViewById(R.id.receipt_textview);
        }
    }
}
