package com.example.sqlitedatabaseapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlitedatabaseapplication.model.Receipt;

public class ReceiptDatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "receipts.db";
    private static final String TABLE_NAME = "receipts";

    //strictly > 0
    private static int DATABASE_VERSION = 1;

    //column names in table
    public static final String COLUMN_RECEIPT_ID = "receipt_id";
    public static final String COLUMN_RECEIPT_TITLE = "receipt_title";
    public static final String COLUMN_RECEIPT_PRICE = "receipt_price";


    //name--> database name
    //context--> whatever context is calling it, can be null
    //factory--> Cursor object
    //version--> whatever version of the database you are using
    public ReceiptDatabaseHelper(@Nullable Context context,
                                 //@Nullable String name,
                                 @Nullable SQLiteDatabase.CursorFactory factory//,
                                 //int version) {
                                 ){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement =   "CREATE TABLE "    +   TABLE_NAME+
                " ("+
                COLUMN_RECEIPT_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_RECEIPT_TITLE+" TEXT, "+
                COLUMN_RECEIPT_PRICE+" TEXT"+
                ")";
        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DATABASE_VERSION = newVersion;
        String updateQuery = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(updateQuery);
        onCreate(db);
    }

    public void insertReceipt(Receipt receipt){
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_RECEIPT_TITLE, receipt.getTitle());
        contentValues.put(COLUMN_RECEIPT_PRICE, receipt.getPrice());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public void deleteReceipt(Receipt receipt){
        String deleteStatement = "DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_RECEIPT_ID+" = "+receipt.getId();
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(deleteStatement);
    }

    public Cursor retrieveReceipt(){
        String selectStatement = "SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();

        Cursor receipts = db.rawQuery(selectStatement,null);

        //db.close();

        return receipts;

    }


    public void close(){
        SQLiteDatabase db = getReadableDatabase();
        db.close();
    }

}
