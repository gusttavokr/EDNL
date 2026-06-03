package Classes.Grafos;

import java.util.ArrayList;

public class Aresta{
    private Vertice verticeInicio;
    private Vertice verticeFim;
    private Object element;
    private Boolean direcionada;

    private ArrayList<Vertice> vertices;

    public Aresta(Object elemento, Vertice vertice1, Vertice vertice2){
        this.element = elemento;
        this.verticeInicio = vertice1;
        this.verticeFim = vertice2;

        this.vertices = new ArrayList<>();
        vertices.add(vertice1);
        vertices.add(vertice2);
        
        direcionada = false;
    }

    // GETTERS
    public Vertice getVerticeInicio(){
        return this.verticeInicio;
    }
    public Vertice getVerticeFim(){
        return this.verticeFim;
    }
    public Object getElement(){
        return this.element;
    }
    public boolean getDirecionada(){
        return this.direcionada;
    }
    public ArrayList<Vertice> getVertices(){
        return this.vertices;
    }

    //SETTERS
    public void setVerticeInicio(Vertice v){
        this.verticeInicio = v;
    }
    public void setVerticeFim(Vertice v){
        this.verticeFim = v;
    }
    public void setElement(Object o){
        this.element = o;
    }
    public void setDirecionada(boolean x){
        this.direcionada = x;
    }
    public void addVertice(Vertice v){
        this.vertices.add(v);
    }
    public void removeVertice(Vertice v){
        this.vertices.remove(v);
    }
}