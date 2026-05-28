package Classes.Grafos;

import java.util.ArrayList;
import java.util.List;

public class Vertice{
    private Object identificador; //v1, v2
    private List<Aresta> arestas;

    public Vertice(Object id){
        this.identificador = id;
        this.arestas = new ArrayList<>();
    }
}