package com.example.thiagobaucet1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class PostoX extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "PostoX";
    private static final int DATABASE_VERSION = 5   ;

    //Table Names
    private static final String TABLE_Vacina = "Vacina";
    private static final String TABLE_Vacinados = "Vacinados";

    //Vacina Table Columns
    private static final String Column_vacinaId = "vacinaid";
    private static final String Column_nomeVacina = "nomeV";
    private static final String Column_fabricante = "fabricante";

    //Vacinados Table Columns
    private static final String Column_vacinadoId = "numVacinado";
    private static final String Column_nomePessoa = "nomeP";
    private static final String Column_cpf = "cpf";
    private static final String Column_idade = "idade";
    private static final String Column_vidFK = "vacid";

    SQLiteDatabase db;

    public PostoX(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String TABLE_CREATE1 = "create table Vacina"+
                "(vacinaId integer primary key autoincrement, nomeV text not null," +
                "fabricante text not null);";
        String TABLE_CREATE2 = "create table Vacinados"+
                "(numVacinado integer primary key autoincrement,"+
                "nomeP text not null, cpf text not null, idade integer not null,"+
                "vacid integer not null, foreign key(vacid) references Vacina(vacinaId));";
        db.execSQL(TABLE_CREATE1);
        db.execSQL(TABLE_CREATE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Vacina);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Vacinados);
        onCreate(db);
    }

    public long insereVacina(Vacina v){
        long retornoBD;
        db=this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(Column_nomeVacina,v.getNomeV());
        val.put(Column_fabricante,v.getFabricante());
        retornoBD = db.insert(TABLE_Vacina, null, val);
        String res = Long.toString(retornoBD);
        Log.i("PostoX",res);
        db.close();
        return retornoBD;
    }

    public long inserePessoa(Pessoa p){
        long retornoBD;
        db=this.getWritableDatabase();
        ContentValues val =new ContentValues();
        val.put(Column_nomePessoa,p.getNomeP());
        val.put(Column_cpf,p.getCpf());
        val.put(Column_idade,p.getIdade());
        val.put(Column_vidFK,p.getVacid());
        retornoBD = db.insert(TABLE_Vacinados, null, val);
        String res = Long.toString(retornoBD);
        Log.i("PostoX", res);
        db.close();
        return retornoBD;
    }

    public ArrayList<Vacina> selectAllVacina(){
        String[] coluns = {Column_vacinaId, Column_nomeVacina, Column_fabricante};
        Cursor cur = getReadableDatabase().query(TABLE_Vacina,coluns,
                null, null, null, null,
                "upper(NomeV)", null);
        ArrayList<Vacina> listaVacina = new ArrayList<Vacina>();
        while (cur.moveToNext()){
            Vacina v = new Vacina();
            v.setVacinaId(cur.getInt(0));
            v.setNomeV(cur.getString(1));
            v.setFabricante(cur.getString(2));
            listaVacina.add(v);
        }
        return listaVacina;
    }

    public ArrayList<Pessoa> selectAllPessoa(){
        String[] coluns = {Column_vacinadoId,Column_nomePessoa,Column_cpf,Column_idade,Column_vidFK};

        Cursor cur = getReadableDatabase().query(TABLE_Vacinados,coluns,
                null, null,  null, null,
                "upper(nomeP)", null);
        ArrayList<Pessoa> listaPessoa = new ArrayList<Pessoa>();
        while(cur.moveToNext()){
            Pessoa p= new Pessoa();
            p.setNumVacinado(cur.getInt(0));
            p.setNomeP(cur.getString(1));
            p.setCpf(cur.getString(2));
            p.setIdade(cur.getInt(3));
            p.setVacid(cur.getInt(4));
            listaPessoa.add(p);
        }
        return listaPessoa;
    }

    public long alteraVacina(Vacina vacina){
        long retornoBD;
        db = this.getWritableDatabase();
        ContentValues val =new ContentValues();
        val.put(Column_nomeVacina,vacina.getNomeV());
        val.put(Column_fabricante,vacina.getFabricante());
        String[] args ={String.valueOf(vacina.getVacinaId())};
        retornoBD = db.update(TABLE_Vacina, val, "vacinaId=?",args);
        db.close();
        return retornoBD;
    }

    public long alteraPessoa(Pessoa pessoa){
        long retornoBD;
        db = this.getWritableDatabase();
        ContentValues val =new ContentValues();
        val.put(Column_nomePessoa,pessoa.getNomeP());
        val.put(Column_cpf,pessoa.getCpf());
        val.put(Column_idade,pessoa.getIdade());
        val.put(Column_vidFK,pessoa.getVacid());
        String[] args ={String.valueOf(pessoa.getNumVacinado())};
        retornoBD = db.update(TABLE_Vacinados, val, "numVacinado=?",args);
        db.close();
        return retornoBD;
    }


    public long excluirVacina(Vacina v){
        long retornoBD;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(v.getVacinaId())};
        retornoBD = db.delete(TABLE_Vacina,Column_vacinaId+"=?",args);
        return retornoBD;
    }

    public long excluirPessoa(Pessoa p){
        long retornoBD;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(p.getNumVacinado())};
        retornoBD = db.delete(TABLE_Vacinados,Column_vacinadoId+"=?",args);
        return retornoBD;
    }
}
