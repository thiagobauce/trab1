package com.example.thiagobaucet1;

import java.io.Serializable;

public class Vacina implements Serializable{
    private int vacinaId;
    private String nomeV;
    private String fabricante;

    public Vacina(){ }

    public int getVacinaId() { return vacinaId; }

    public void setVacinaId(int vacinaId) { this.vacinaId = vacinaId; }

    public String getNomeV() {
        return nomeV;
    }

    public void setNomeV(String nomeV) {
        this.nomeV = nomeV;
    }

    public String getFabricante() { return fabricante;}

    public void setFabricante(String fabricante) { this.fabricante = fabricante;}

    @Override
    public String toString(){
        return nomeV.toString()+": fabrincante: "+fabricante.toString();
    }
}