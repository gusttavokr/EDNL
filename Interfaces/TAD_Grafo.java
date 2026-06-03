package Interfaces;

import java.util.ArrayList;
import java.util.Iterator;

import Classes.Grafos.Aresta;
import Classes.Grafos.Vertice;

public interface TAD_Grafo {
    // Grafo Não direcionado
    
    // ===== MÉTODOS DE VÉRTICE
    public ArrayList<Vertice> finalVertices(Aresta a);     // Retorna um array armazenando os vertices de uma aresta
    public ArrayList<Aresta> arestasIncidentes(Vertice v); // quais arestas tem esse vertice
    public Vertice oposto(Vertice v, Aresta a);            // Retorna o vertice oposto a v
    public boolean isAdjacente(Vertice v, Vertice w);      // Retorna true se tiverem aresta ligando eles
    public int grau(Vertice v);                            // Usar arestas incidentes
    
    public Iterator<Vertice> vertices();                   // Usar DFs
    
    public Vertice inserirVertice(Object o);               // pode ter um 4 parametro, se é direcionado ou nao
    public Vertice removerVertice(Vertice v);
    public Object substituirVertice(Vertice v, Object x);  // é um replace, vale para aresta também

    // ===== MÉTODOS DE ARESTA
    public Iterator<Aresta> arestas(); // Usar BFs

    public Aresta inserirAresta(Vertice v1, Vertice v2, Object o);
    public Aresta removerAresta(Aresta a);
    public Object substituirAresta(Aresta a, Object x);
    
    // ===== MÉTODOS PARA DIRECIONADO
    public boolean isDirecionado(Aresta a);
    public Aresta inserirArestaDirecionada(Vertice v1, Vertice v2, Object o, boolean True); // inserirArestaDirecionada(v1, v2, "X") - A diferença está na operação
    public int grauEntrada(Vertice v);
    public int grauSaída(Vertice v);
}
