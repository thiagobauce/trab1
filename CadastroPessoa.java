package com.example.thiagobaucet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastroPessoa extends AppCompatActivity {
    private EditText edtNomeP, edtCPF, edtIdade;
    private Button btnVariavel2;
    private Spinner spinner;
    Pessoa pessoa, altp;
    PostoX ps;
    ArrayAdapter<Vacina> arrayAdapterVacina;
    ArrayList<Vacina> arrayListVacina;
    Vacina vacina;
    long retorno1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);

        Intent it= getIntent();
        altp = (Pessoa) it.getSerializableExtra("person");

        pessoa = new Pessoa();
        ps = new PostoX(CadastroPessoa.this);
        arrayListVacina = ps.selectAllVacina();

        edtNomeP = findViewById(R.id.edtNomeP);
        edtCPF = findViewById(R.id.edtCPF);
        edtIdade = findViewById(R.id.edtIdade);
        btnVariavel2 =findViewById(R.id.btnVariavel2);
        spinner = findViewById(R.id.spinner);

        arrayAdapterVacina =new ArrayAdapter<Vacina>(CadastroPessoa.this,
                android.R.layout.simple_list_item_1,arrayListVacina);
        spinner.setAdapter(arrayAdapterVacina);

        if(altp != null){
            btnVariavel2.setText("ALTERAR");
            edtNomeP.setText(altp.getNomeP());
            edtCPF.setText(altp.getCpf());
            edtIdade.setText(altp.getIdade()+"");
            spinner.getSelectedItemPosition();
            pessoa.setNumVacinado(altp.getNumVacinado());
        }
        else{
            btnVariavel2.setText("SALVAR");
        }
        btnVariavel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edtNomeP.getText().toString();
                String cpf = edtCPF.getText().toString();
                String idade = edtIdade.getText().toString();
                int vacid = spinner.getSelectedItemPosition();
                long retorno1;
                pessoa.setNomeP(nome);
                pessoa.setCpf(cpf);
                pessoa.setIdade(Integer.parseInt(idade));
                pessoa.setVacid(vacid);
                if(btnVariavel2.getText().toString().equals("SALVAR")){
                    retorno1 =ps.inserePessoa(pessoa);
                    if (retorno1 == -1){
                        alert("Erro ao Cadastrar!");
                    }
                    else{
                        alert("Cadastro realizado com sucesso!");
                    }
                }
                else{
                    ps.alteraPessoa(pessoa);
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