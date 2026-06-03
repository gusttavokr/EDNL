package Classes.Grafos;

import java.util.ArrayList;

import Exceptions.GrafoErro;
import Interfaces.TAD_Grafo;

public class Grafo implements TAD_Grafo{

    public ArrayList<Vertice> finalVertices(Aresta a){
        return a.getVertices();
    }

    
}
