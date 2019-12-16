package com.example.sqlitedatabaseapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sqlitedatabaseapplication.R;
import com.example.sqlitedatabaseapplication.adapter.RecyclerViewAdapter;
import com.example.sqlitedatabaseapplication.database.ReceiptDatabaseHelper;
import com.example.sqlitedatabaseapplication.model.Receipt;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ReceiptDelegate, ViewReceiptFragment.ReceiptFragmentListener {

    private ReceiptDatabaseHelper receiptDatabase;
    private List<Receipt> receiptList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ViewReceiptFragment viewReceiptFragment = new ViewReceiptFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intializer();
    }

    private void intializer(){
        drawRecyclerView();

        receiptDatabase = new ReceiptDatabaseHelper(this,null);
//        receiptDatabase.insertReceipt(new Receipt("The Battery Expense","$500"));
//        receiptDatabase.insertReceipt(new Receipt("The Battery Expense1","$50"));
//        receiptDatabase.insertReceipt(new Receipt("The Battery Expense2","$500"));
//        receiptDatabase.insertReceipt(new Receipt("The Battery Expense3","$300"));
        receiptDatabase.insertReceipt(new Receipt("The Battery Expense 70707070707","$532"));

        Cursor receipts = receiptDatabase.retrieveReceipt();
        receipts.moveToFirst();

        //Log.d("TAG_X", "Receipts mtF: "+bool);//+ "  Receipts mtN: "+receipts.moveToNext());

        //cursor is a hashmap for the id to the column number values
        String text = "";
        while(receipts.moveToNext()){
            int receiptId = receipts.getInt(receipts.getColumnIndex(ReceiptDatabaseHelper.COLUMN_RECEIPT_ID));
            String receiptTitle = receipts.getString(receipts.getColumnIndex(ReceiptDatabaseHelper.COLUMN_RECEIPT_TITLE));
            String receiptPrice = receipts.getString(receipts.getColumnIndex(ReceiptDatabaseHelper.COLUMN_RECEIPT_PRICE));
            Receipt receipt = new Receipt(receiptId,receiptTitle,receiptPrice);
            receiptList.add(receipt);
            //Log.d("TAG_X", "receipt: "+receiptTitle+", "+receiptPrice);
        }

        receiptDatabase.close();
//        while(receipts.moveToNext()){
//            Receipt receipt = receipts.get
//            text += receipts.
//        }
    }

    private void drawRecyclerView(){
        recyclerView = findViewById(R.id.recyclerViewMainActivity);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter adapter = new RecyclerViewAdapter(receiptList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void deleteItem(int position) {
        Receipt r = receiptList.remove(position);
        receiptDatabase.deleteReceipt(r);
        recyclerView.getAdapter().notifyItemRemoved(position);
    }

    @Override
    public void viewItem(Receipt receipt) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("my_key",receipt);

        viewReceiptFragment.setArguments(bundle);

        Log.d("TAG_Y", "Fragment should be attaching ");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frameLayout,viewReceiptFragment)
                .addToBackStack(viewReceiptFragment.getTag())
                .commit();
    }

    @Override
    public void makeToast(String price) {
        getSupportFragmentManager().popBackStack();
        Toast.makeText(this,"The price : "+price,Toast.LENGTH_SHORT).show();
    }
}
