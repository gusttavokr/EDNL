package Tests;

import Classes.Grafos.Grafo;
import Classes.Grafos.Vertice;

public class TesteGrafo {
    public static void main(String[] args) {
     
        System.out.println("Testes para Grafo Simples");

        Grafo grafo = new Grafo();

        Object um = "1";
        Object dois = "2";
        Object tres = "3";

        Vertice Vertice1 = grafo.inserirVertice(um);
        Vertice Vertice2 = grafo.inserirVertice(dois);
        Vertice Vertice3 = grafo.inserirVertice(tres);

        grafo.inserirAresta(Vertice1, Vertice2, "Aresta1");

        grafo.print();
    }
}
