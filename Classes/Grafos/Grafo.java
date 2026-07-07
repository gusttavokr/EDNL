package Classes.Grafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

    public Iterator<Vertice> vertices(){
        return this.vertices.iterator();
    }

    public int quantidadeVertices(){

        Iterator<Vertice> vertices = vertices();

        int tamanho = 0;
        while (vertices.hasNext()) {
            vertices.next();
            tamanho++;
        }

        return tamanho;
    }

    public Iterator<Aresta> arestas(){
        return this.arestas.iterator();
    }

    public Vertice inserirVertice(Object o){

        Iterator<Vertice> vertices = vertices();

        while (vertices.hasNext()) {
            Vertice i = vertices.next();

            if (Objects.equals(o, i.getElement())) {
                throw new GrafoErro("Esse elemento já está presente no Grafo");
            }
        }        
        
        Vertice vertice = new Vertice(o);
        this.vertices.add(vertice);
        return vertice;
    }

    public Vertice removerVertice(Vertice v){
        for (Vertice v1 : this.vertices){
            if (v1 == v) {
                this.vertices.remove(v);
                return v;
            }
        }

        throw new GrafoErro("Vértice não encontrado.");
    }

    public Object substituirVertice(Vertice v, Object x){
        Object old = v.getElement();
        v.setElement(x);
        return old;
    }

    public Object substituirAresta(Aresta a, Object x){
        Object old = a.getElement();
        a.setElement(x);
        return old;
    }

    public Aresta inserirAresta(Vertice v1, Vertice v2, Object element){
        Aresta a = new Aresta(element, v1, v2);

        v1.addAresta(a);
        v2.addAresta(a);

        return a;
    }

    public Aresta removerAresta(Aresta a){
        ArrayList<Vertice> vertices = finalVertices(a);
        Vertice v1 = vertices.get(0);
        Vertice v2 = vertices.get(-1);

        v1.removeAresta(a);
        v2.removeAresta(a);

        return a;
    }

    public boolean isDirecionado(Aresta a){

        if (a.getDirecionada() == true) {
            return true;
        }

        return false;
    }

    public Aresta inserirArestaDirecionada(Vertice v1, Vertice v2, Object o){
        Aresta a = inserirAresta(v1, v2, o);
        a.setDirecionada(true);
        return a;
    }

    public int grauEntrada(Vertice v){
        int count = 0;
        ArrayList<Aresta> arestas = arestasIncidentes(v);

        for (Aresta a : arestas){
            if (v == a.getVerticeFim()) {
                count += 1;
            }
        }

        return count;
    }

    public int grauSaida(Vertice v){
        int count = 0;
        ArrayList<Aresta> arestas = arestasIncidentes(v);

        for (Aresta a : arestas){
            if (v == a.getVerticeInicio()) {
                count += 1;
            }
        }

        return count;
    }

    // Retorna uma lista de vertices adjacentes ao Vertice v
    public ArrayList<Vertice> verticesAdjacentes(Vertice v){

        ArrayList<Vertice> listaVertices = new ArrayList<>();

        ArrayList<Aresta> arestas = arestasIncidentes(v);

        for (Aresta a : arestas){
            Vertice oposto = oposto(v, a);
            listaVertices.add(oposto);
            // System.out.println(oposto.getElement());
            
        }

        return listaVertices;

    }

    
    public void print(){

        // TODO: Atualmente, o print não printa os elementos, imprime os endereços de memória
        for (Vertice v : vertices){

            ArrayList<Vertice> verticesAdj = verticesAdjacentes(v);
            
            System.out.println("[" + v.getElement() + "] = " + verticesAdj.toString());
            
        }
    }
    
    public Aresta nossaAresta(Vertice v1, Vertice v2){

        ArrayList<Aresta> arestas_in = arestasIncidentes(v1);

        for (Aresta a : arestas_in){
            if (a.getVerticeInicio() == v2 || a.getVerticeFim() == v2) {
                return a;   
            }
        }

        return null;
    }

    public int comparar(Object o, Object p) {
        int oInt = converterInt(o);
        int pInt = converterInt(p);

        return Integer.compare(oInt, pInt);
    }

    public int converterInt(Object p) {

        if (p instanceof Integer) {
            return (Integer) p;
        }
        if (p instanceof Float) {
            return Math.round((Float) p);
        }
        if (p instanceof String) {
            return Integer.parseInt((String) p);
        }
        if (p instanceof Boolean) {
            return ((Boolean) p) ? 1 : 0;
        }
        
        return 0;
    }  

    Integer infinito = Integer.MAX_VALUE;

    public ArrayList<Vertice> dijkstra(Vertice origem, Vertice destino){
                
        ArrayList<Integer> custos_distancias = new ArrayList<>();

        ArrayList<Vertice> antecessores = new ArrayList<>();
        
        // PASSO 1 - Início
        for (Vertice v : this.vertices) {
            if (v == origem) {
                custos_distancias.add(0);
            } else{
                custos_distancias.add(infinito);
            } 
            
            antecessores.add(null);
        }
        
        ArrayList<Vertice> vertices_adj = verticesAdjacentes(origem);
        Vertice atual = origem;

        // Ainda existem vertices não processados?
        while (this.vertices.stream().anyMatch(v -> !v.getProcessado())) {

            if (atual == destino) {
                break;
            }

            // PASSO 2 - Escolher o vertice 
            for (Vertice v : vertices_adj){
                
                // Custo atual
                int indice_atual = this.vertices.indexOf(atual);
                int custo_atual = (int) custos_distancias.get(indice_atual);

                // Pro vizinho
                Aresta a = nossaAresta(atual, v);
                int novo_custo = (int) a.getElement();

                // Pro vizinho atualmente
                int indice_v = this.vertices.indexOf(v);
                int custo_v_atual = (int) custos_distancias.get(indice_v);

                int soma = custo_atual + novo_custo;

                // relaxamento
                if (soma < custo_v_atual) {
                    custos_distancias.set(indice_v, soma);
                    antecessores.set(indice_v, atual);
                }

            }

            atual.setProcessado(true);
            
            // PASSO 3 - Qual o menor?
            Vertice proximo = null;
            int menor_custo = infinito;

            for (Vertice v : this.vertices){
                if (!v.getProcessado()) {
                    int indice_v = this.vertices.indexOf(v);
                    int custo_v = (int) custos_distancias.get(indice_v);

                    if (custo_v < menor_custo) {
                        menor_custo = custo_v;
                        proximo = v;
                    }
                }
            }

            if(proximo == null) {
                break;
            }

            atual = proximo;
            vertices_adj = verticesAdjacentes(atual);

            // int indice_atual = this.vertices.indexOf(atual);
            // int custo_atual = (int) custos_distancias.get(indice_atual);
            // Object menor_custo = custo_atual;
            
            // for (Vertice v : this.vertices){
            //     if (v.getProcessado() == false) {
            //         Integer indice_v = this.vertices.indexOf(v);
                    
            //         Object custo_v = custos_distancias.get(indice_v);
                    
            //         if (comparar(custo_v, menor_custo) < 0) {
            //             menor_custo = custo_v;
            //             atual = v;
            //         }
            //     }
            // }
            // vertices_adj = verticesAdjacentes(atual);
        }

        ArrayList<Vertice> caminho = new ArrayList<>();
        Vertice fim = destino;

        while (fim != null) {
            caminho.add(fim);

            int indice = this.vertices.indexOf(fim);
            fim = antecessores.get(indice);
        }

        Collections.reverse(caminho);

        resetVertices();

        return caminho;
    }

    public ArrayList<Vertice> the_star(Vertice origem, Vertice destino){

        ArrayList<Integer> custos_distancias = new ArrayList<>();
        ArrayList<Vertice> antecessores = new ArrayList<>();
        
        ArrayList<Vertice> abertos = new ArrayList<>();
        
        

        // PASSO 1 - Início
        for (Vertice v : this.vertices) {
            if (v == origem) {
                custos_distancias.add(0);
            } else{
                custos_distancias.add(infinito);
            } 
            
            antecessores.add(null);
        }

        abertos.add(origem);

        while (!abertos.isEmpty()) {

            Vertice atual = abertos.get(0);
            int melhor = custos_distancias.get(this.vertices.indexOf(atual)) + heuristica(atual, destino);
            
            for (Vertice v : abertos){

                int indice = this.vertices.indexOf(v);
                int g = custos_distancias.get(indice);
                int f = g + heuristica(v, destino);
                
                if (f < melhor) {
                    melhor = f;
                    atual = v;
                }

            }   

            if (atual == destino) {
                break;
            }

            abertos.remove(atual);
            atual.setProcessado(true);

            ArrayList<Vertice> vertices_adj = verticesAdjacentes(atual);

            for(Vertice v : vertices_adj){

                if (v.getProcessado()) {
                    continue;
                }

                int indice_atual = this.vertices.indexOf(atual);
                int indice_v = this.vertices.indexOf(v);

                int custo_atual = custos_distancias.get(indice_atual);
                int custo_aresta = (int) nossaAresta(atual, v).getElement();

                int novoG = custo_aresta + custo_atual;

                // relaxamento
                if (novoG < custos_distancias.get(indice_v)) {
                    custos_distancias.set(indice_v, novoG);
                    antecessores.set(indice_v, atual);

                    if (!abertos.contains(v)) {
                        abertos.add(v);
                    }
                }

            }
        }

        ArrayList<Vertice> caminho = new ArrayList<>();
        Vertice atual = destino;

        while (atual != null) {
            caminho.add(atual);

            int indice = this.vertices.indexOf(atual);
            atual = antecessores.get(indice);
        }

        Collections.reverse(caminho);

        resetVertices();

        return caminho;
    }

    public int heuristica(Vertice inicio, Vertice fim){

        int inicio_x = inicio.getX();
        int inicio_y = inicio.getY();
        int fim_x = fim.getX();
        int fim_y = fim.getY();

        int heuristica = Math.abs(inicio_x - fim_x) + Math.abs(inicio_y - fim_y);

        return heuristica;
    }

    public void resetVertices(){
        for (Vertice v : this.vertices){
            if (v.getProcessado() == true) {
                v.setProcessado(false);
            }
        }
    }
}
