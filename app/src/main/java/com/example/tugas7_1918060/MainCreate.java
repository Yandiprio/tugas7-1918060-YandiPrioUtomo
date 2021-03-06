package com.example.tugas7_1918060;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainCreate extends AppCompatActivity {
    private MyDatabase db;
    private EditText Enama, Ekelas;
    private String Snama, Skelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create);
        db = new MyDatabase(this);
        Enama = (EditText) findViewById(R.id.createproduk);
        Ekelas = (EditText) findViewById(R.id.createharga);
        Button btnCreate = (Button)
                findViewById(R.id.createbtn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snama = String.valueOf(Enama.getText());
                Skelas = String.valueOf(Ekelas.getText());
                if (Snama.equals("")) {
                    Enama.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi nama", Toast.LENGTH_SHORT).show();
                } else if (Skelas.equals("")) {
                    Ekelas.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi kelas",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Enama.setText("");
                    Ekelas.setText("");
                    Toast.makeText(MainCreate.this, "Data telah ditambah",
                            Toast.LENGTH_SHORT).show();
                    db.Createminuman(new minuman(null, Snama, Skelas));
                    Intent a = new Intent(MainCreate.this, MainActivity.class);
                    startActivity(a);
                }
            }
        });
    }
}
