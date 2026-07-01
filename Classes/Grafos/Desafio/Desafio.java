package Classes.Grafos.Desafio;

import java.util.ArrayList;

import Classes.Grafos.Vertice;

public class Desafio {
    
    public enum TipoBusca{
        A_ESTRELA,
        DIJKSTRA
    }

    private int[][] mapa;
    private Vertice inicio;
    private ArrayList<Vertice> saidas;
    private TipoBusca tipo;

    public Desafio(int[][] m, Vertice i, ArrayList<Vertice> s, TipoBusca t){

        this.mapa = m;
        this.inicio = i;
        this.saidas = s;
        this.tipo = t; 
    }


}
