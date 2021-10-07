package com.example.thiagobaucet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroVacina extends AppCompatActivity {
    private EditText edtNomeV, edtFabric;
    private Button btnVariavel;
    Vacina v, altv;
    PostoX ps;
    long retorno1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_vacina);

        Intent it= getIntent();
        altv = (Vacina) it.getSerializableExtra("chave-vacina");

        v = new Vacina();
        ps = new PostoX(CadastroVacina.this);

        edtNomeV = findViewById(R.id.edtNomeV);
        edtFabric = findViewById(R.id.edtFabricante);

        btnVariavel =findViewById(R.id.btnVariavel);

        if(altv != null){
            btnVariavel.setText("ALTERAR");
            edtNomeV.setText(altv.getNomeV());
            edtFabric.setText(altv.getFabricante());
            v.setVacinaId(altv.getVacinaId());
        }
        else{
            btnVariavel.setText("SALVAR");
        }
        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edtNomeV.getText().toString();
                String fabricante = edtFabric.getText().toString();
                long retorno1;
                v.setNomeV(nome);
                v.setFabricante(fabricante);
                if(btnVariavel.getText().toString().equals("SALVAR")){
                    retorno1 =ps.insereVacina(v);
                    if (retorno1 == -1){
                        alert("Erro ao Cadastrar!");
                    }
                    else{
                        alert("Cadastro realizado com sucesso!");
                    }
                }
                else{
                    ps.alteraVacina(v);
                    ps.close();
                }
                finish();
            }
        });
    }
    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}