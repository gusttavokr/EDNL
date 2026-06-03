package Classes.Grafos;

import java.util.ArrayList;
import java.util.Objects;

import Exceptions.GrafoErro;
import Interfaces.TAD_Grafo;

public class Grafo implements TAD_Grafo{

    public ArrayList<Aresta> arestas;
    public ArrayList<Vertice> vertices;

    public Grafo(){
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    public ArrayList<Vertice> finalVertices(Aresta a){
        return a.getVertices();
    }

    public ArrayList<Aresta> arestasIncidentes(Vertice v){
        return v.getArestas();
    }

    public Vertice oposto(Vertice v, Aresta a){

        // if (a == null || v == null) {
        //     throw new GrafoErro("Vértice ou Aresta inexistente");
        // }

        Vertice vertice1 = a.getVerticeInicio();
        Vertice vertice2 = a.getVerticeFim();
        
        if (v == vertice1) {
            return vertice2;
        } else if (v == vertice2){
            return vertice1;
        }

        throw new GrafoErro("O vértice: " + v + " não está na aresta.");  
    }

    public boolean isAdjacente(Vertice v1, Vertice v2){
        
        ArrayList<Aresta> arestasV1 = arestasIncidentes(v1);

        for (Aresta aresta : arestasV1){
            if (v2 == oposto(v1, aresta)) {
                return true;
            }
        }

        return false;
    }

    public int grau(Vertice v){
        ArrayList<Aresta> arestas = arestasIncidentes(v);
        return arestas.size();
    }

    public Vertice inserirVertice(Object o){
        
        for (Vertice v : this.vertices){
            if (Objects.equals(v, o)) {
                throw new GrafoErro("Esse elemento já está presente no Grafo");
            }
        }
        
        Vertice vertice = new Vertice(o);
        this.vertices.add(vertice);
        return vertice;
    }
}
