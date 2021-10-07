package com.example.thiagobaucet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PessoaView extends AppCompatActivity {
    private Button btnNovaPessoa;
    private ListView listPessoa;
    Pessoa pessoa;
    PostoX ps;
    ArrayList<Pessoa> arrayListPessoa;
    ArrayAdapter<Pessoa> arrayAdapterPessoa;
    private int id1,id2;                    /*menu item*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_view);
        listPessoa =findViewById(R.id.listPessoa);
        registerForContextMenu(listPessoa);
        btnNovaPessoa = findViewById(R.id.btnNovaPessoa);
        btnNovaPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(PessoaView.this,CadastroPessoa.class);
                startActivity(it);
            }
        });
        listPessoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Pessoa pessoaEnviada = (Pessoa) arrayAdapterPessoa.getItem(position);
                Intent it = new Intent(PessoaView.this,CadastroPessoa.class);
                it.putExtra("person",pessoaEnviada);
                startActivity(it);
            }
        });
        listPessoa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                pessoa =arrayAdapterPessoa.getItem(i);
                return false;
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        preencheLista();
    }
    public void preencheLista(){
        ps = new PostoX(PessoaView.this);
        arrayListPessoa = ps.selectAllPessoa();
        ps.close();
        if(listPessoa!=null){
            arrayAdapterPessoa = new ArrayAdapter<Pessoa>(PessoaView.this,
                    android.R.layout.simple_list_item_1,arrayListPessoa);
            listPessoa.setAdapter(arrayAdapterPessoa);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        MenuItem mDelete = menu.add(Menu.NONE, id1, 1,"Deleta Registro");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2,"Cancela");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                long retornoBD;
                ps = new PostoX(PessoaView.this);
                retornoBD = ps.excluirPessoa(pessoa);
                ps.close();
                if(retornoBD==-1){
                    alert("Erro de exclusão!");
                }
                else{
                    alert("Registro excluído com sucesso!");
                }
                preencheLista();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}