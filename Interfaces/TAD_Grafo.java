package Interfaces;

import java.util.ArrayList;
import java.util.Iterator;

import Classes.Grafos.Aresta;
import Classes.Grafos.Vertice;

public interface TAD_Grafo {
    // Grafo Não direcionado
    
    // finalVertices(a), retorna um array armazenando os vertices de uma aresta
    public ArrayList<Vertice> finalVertices(Aresta a);

    // oposto(v, e) retorna o vertice oposto a v
    public Vertice oposto(Vertice v, Aresta a);

    // éAdjacente(v, w) retorna true se tiverem aresta ligando eles
    public boolean isAdjacente(Vertice v, Vertice w);

    //substituir(v, x) é um replace, vale para aresta também
    public Object substituirVertice(Vertice v, Object x);
    public Object substituirAresta(Aresta a, Object x);

    // Inserção
    public Vertice inserirVertice(Object o);
    public Aresta inserirAresta(Vertice v1, Vertice v2, Object o);
    // inserirVertice(v1, v2, o) pode ter um 4 parametro, se é direcionado ou nao

    // Remoção
    public Vertice removerVertice(Vertice v);
    public Aresta removerAresta(Aresta a);

    // arestasIncidentes(v), quais arestas tem esse vertice
    public ArrayList<Aresta> arestasIncidentes(Vertice v);

    
    // Iteradores de Vertices e arestas - Usar: BFs e DFs
    public Iterator<Vertice> vertices();
    public Iterator<Aresta> arestas();

    // Grau de um nó - utilizar arestas incidentes
    public int grau(Vertice v);

    //  ========================== DIRECIONADO
    // ehDirecionado(e) 
    public boolean isDirecionado(Aresta a);

    // inserirArestaDirecionada(v1, v2, "X") - A diferença está na operação
    public Aresta inserirArestaDirecionada(Vertice v1, Vertice v2, Object o, boolean True);

    //grau de entrada - utilizar arestas incidentes
    public int grauEntrada(Vertice v);
    //grau de saida
    public int grauSaída(Vertice v);
}
