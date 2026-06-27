package Classes.Grafos;

import java.util.ArrayList;

public class Vertice{
    private Object element;
    
    private ArrayList<Aresta> arestas;
    
    private boolean processado;

    public Vertice(Object x){
        this.element = x;
        this.arestas = new ArrayList<>();
        this.processado = false;
    }

    // GETTERS
    
    public Object getElement(){
        return this.element;
    }
    public ArrayList<Aresta> getArestas(){
        return this.arestas;
    }

    public Boolean getProcessado(){
        return this.processado;
    }

    //SETTERS
    
    public void setElement(Object element){
        this.element = element;
    }
    public void addAresta(Aresta aresta){
        this.arestas.add(aresta);
    }
    public void removeAresta(Aresta aresta){
        this.arestas.remove(aresta);
    }
    public void setProcessado(Boolean x){
        this.processado = x;
    }

}