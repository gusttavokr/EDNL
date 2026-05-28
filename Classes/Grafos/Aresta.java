package Classes.Grafos;

public class Aresta{
    private Vertice verticeInicio;
    private Vertice verticeFim;
    private Object element;
    private Boolean direcionada;

    private Vertice[] vertices;

    public Aresta(Object elemento, Vertice vertice1, Vertice vertice2){
        this.element = elemento;
        this.verticeInicio = vertice1;
        this.verticeFim = vertice2;

        Vertice[] verticesList = new Vertice[]{vertice1, vertice2};
        this.vertices = verticesList;
        
        direcionada = false;
    }

}