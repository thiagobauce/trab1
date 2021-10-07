package com.example.thiagobaucet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnListarP;
    private Button btnListarV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListarV = findViewById(R.id.btnVacina);
        btnListarP = findViewById(R.id.btnPessoa);
        btnListarP.setOnClickListener((View v) -> {
            Intent it = new Intent(MainActivity.this, PessoaView.class);
            startActivity(it);
        });
        btnListarV.setOnClickListener((View v) -> {
            Intent it = new Intent(MainActivity.this, VacinaView.class);
            startActivity(it);
        });
    }
}