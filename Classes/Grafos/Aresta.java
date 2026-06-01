package Classes.Grafos;

import java.util.ArrayList;
import java.util.List;

public class Aresta{
    private Vertice verticeInicio;
    private Vertice verticeFim;
    private Object element;
    private Boolean direcionada;

    private List<Vertice> vertices;

    public Aresta(Object elemento, Vertice vertice1, Vertice vertice2){
        this.element = elemento;
        this.verticeInicio = vertice1;
        this.verticeFim = vertice2;

        this.vertices = new ArrayList<>();
        vertices.add(vertice1);
        vertices.add(vertice2);
        
        direcionada = false;
    }

}