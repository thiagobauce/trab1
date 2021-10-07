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

public class VacinaView extends AppCompatActivity {
    private Button btnNovaVacina;
    private ListView listVacina;
    Vacina vacina;
    PostoX ps;
    ArrayList<Vacina> arrayListVacina;
    ArrayAdapter<Vacina> arrayAdapterVacina;
    private int id1,id2;                    /*menu item*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina_view);

        listVacina =findViewById(R.id.listVacina);
        registerForContextMenu(listVacina);
        btnNovaVacina = findViewById(R.id.btnNovaVacina);
        btnNovaVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(VacinaView.this,CadastroVacina.class);
                startActivity(it);
            }
        });
        listVacina.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Vacina vacinaEnviada = (Vacina) arrayAdapterVacina.getItem(position);
                Intent it = new Intent(VacinaView.this,CadastroVacina.class);
                it.putExtra("chave-vacina", vacinaEnviada);
                startActivity(it);
            }
        });
        listVacina.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                vacina =arrayAdapterVacina.getItem(i);
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
        ps = new PostoX(VacinaView.this);
        arrayListVacina = ps.selectAllVacina();
        ps.close();
        if(listVacina!=null){
            arrayAdapterVacina = new ArrayAdapter<Vacina>(VacinaView.this,
                    android.R.layout.simple_list_item_1,arrayListVacina);
            listVacina.setAdapter(arrayAdapterVacina);
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
                ps = new PostoX(VacinaView.this);
                retornoBD = ps.excluirVacina(vacina);
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