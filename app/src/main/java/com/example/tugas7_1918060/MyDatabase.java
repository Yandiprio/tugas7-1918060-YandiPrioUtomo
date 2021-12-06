package com.example.tugas7_1918060;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_minuman";
    private static final String tb_minuman = "tb_minuman";
    private static final String getTb_minuman_id = "id";
    private static final String getTb_minuman_nama = "nama";
    private static final String getTb_minuman_harga = "harga";
    private static final String CREATE_TABLE_MINUMAN = "CREATE TABLE " + tb_minuman +"("
            + getTb_minuman_id + " INTEGER PRIMARY KEY ,"
            + getTb_minuman_nama + " TEXT ,"
            + getTb_minuman_harga + " TEXT " + ")";
    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MINUMAN);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
    public void Createminuman(minuman data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(getTb_minuman_id, data.get_id());
        values.put(getTb_minuman_nama, data.get_nama_produk());
        values.put(getTb_minuman_harga, data.get_harga());
        db.insert(tb_minuman, null, values);
        db.close();
    }
    public List<minuman> Readminuman() {
        List<minuman> listMhs = new ArrayList<minuman>();
        String selectQuery = "SELECT * FROM " + tb_minuman;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                minuman data = new minuman();
                data.set_id(cursor.getString(0));
                data.set_nama_produk(cursor.getString(1));
                data.set_harga(cursor.getString(2));
                listMhs.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listMhs;
    }
    public int Updateminuman (minuman data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(getTb_minuman_nama, data.get_nama_produk());
        values.put(getTb_minuman_harga, data.get_harga());
        return db.update(tb_minuman, values, getTb_minuman_id +
                        " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }
    public void Deleteminuman(minuman data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_minuman,getTb_minuman_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}
