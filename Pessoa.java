package com.example.thiagobaucet1;

import androidx.annotation.Nullable;

import java.io.Serializable;


public class Pessoa implements Serializable{
    int numVacinado;
    String nomeP;
    String cpf;
    int idade;
    Vacina vacina;
    @Nullable
    int vacid;



    public Pessoa(){

    }
    public Pessoa(String nomeP, int idade, String cpf, int vacid) {
        this.numVacinado = numVacinado;
        this.nomeP = nomeP;
        this.cpf = cpf;
        this.idade = idade;
        this.vacid = vacid;
    }

    public Pessoa(String nomeP, Vacina vacina){
        this.nomeP=nomeP;
        this.vacina=vacina;

    }

    public int getNumVacinado() {
        return numVacinado;
    }

    public void setNumVacinado(int numVacinado) {
        this.numVacinado = numVacinado;
    }

    public String getNomeP() {
        return nomeP;
    }

    public void setNomeP(String nomeP) {
        this.nomeP = nomeP;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getVacid() { return vacid; }

    public void setVacid(int vacid) { this.vacid = vacid; }

    public Vacina getVacina() { return vacina; }

    public void setVacina(Vacina vacina) { this.vacina = vacina; }

    @Override
    public String toString(){
        return nomeP.toString()+" "+idade+" anos\n"+vacina.getNomeV().toString();
    }
}
