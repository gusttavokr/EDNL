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

    // GETTERS
    public int getId(){
        return this.id;
    }
    public Object element(){
        return this.element;
    }
    public List<Aresta> getArestas(){
        return this.arestas;
    }

    //SETTERS
    public void setId(int id){
        this.id = id;
    }
    public void setElement(Object element){
        this.element = element;
    }
    public void addAresta(Aresta aresta){
        this.arestas.add(aresta);
    }
    public void removeAresta(Aresta aresta){
        this.arestas.remove(aresta);
    }

}