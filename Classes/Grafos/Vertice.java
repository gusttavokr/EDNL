package Classes.Grafos;

import java.util.ArrayList;
import java.util.List;

public class Vertice{
    private int id; //v1, v2
    private Object element;

    private List<Aresta> arestas;

    public Vertice(Object x){
        this.element = x;
        this.arestas = new ArrayList<>();
    }
}