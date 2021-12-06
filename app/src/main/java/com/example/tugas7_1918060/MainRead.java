package com.example.tugas7_1918060;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements
        AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapterr adapter_off;
    private MyDatabase db;
    private List<minuman> Listminuman = new
            ArrayList<minuman>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);
        adapter_off = new CustomListAdapterr(this, Listminuman
        );
        mListView = (ListView) findViewById(R.id.list_kaoskaki);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        Listminuman.clear();
        List<minuman> minumanList = db.Readminuman();
        for (minuman mhs : minumanList) {
            minuman daftar = new minuman();
            daftar.set_id(mhs.get_id());
            daftar.set_nama_produk(mhs.get_nama_produk());
            daftar.set_harga(mhs.get_harga());
            Listminuman.add(daftar);
            if ((Listminuman.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int
            i, long l) {
        Object o = mListView.getItemAtPosition(i);
        minuman detailMhs = (minuman) o;
        String Sid = detailMhs.get_id();
        String Snama = detailMhs.get_nama_produk();
        String Sharga = detailMhs.get_harga();
        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama produk", Snama);
        goUpdel.putExtra("Iharga", Sharga);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Listminuman.clear();
        mListView.setAdapter(adapter_off);
        List<minuman> kaoskakis = db.Readminuman();
        for (minuman mhs : kaoskakis) {
            minuman daftar = new minuman();
            daftar.set_id(mhs.get_id());
            daftar.set_nama_produk(mhs.get_nama_produk());
            daftar.set_harga(mhs.get_harga());
            Listminuman.add(daftar);
            if ((Listminuman.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}

