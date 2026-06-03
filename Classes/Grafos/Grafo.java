package Classes.Grafos;

import java.util.ArrayList;

import Exceptions.GrafoErro;
import Interfaces.TAD_Grafo;

public class Grafo implements TAD_Grafo{

    public ArrayList<Vertice> finalVertices(Aresta a){
        return a.getVertices();
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
}
