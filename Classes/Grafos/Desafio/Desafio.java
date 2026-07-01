package Classes.Grafos.Desafio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Classes.Grafos.Aresta;
import Classes.Grafos.Grafo;
import Classes.Grafos.Vertice;

public class Desafio extends Grafo {

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
            int indice_atual = this.vertices.indexOf(atual);
            int custo_atual = (int) custos_distancias.get(indice_atual);
            Object menor_custo = custo_atual;
            
            for (Vertice v : this.vertices){
                if (v.getProcessado() == false) {
                    Integer indice_v = this.vertices.indexOf(v);
                    
                    Object custo_v = custos_distancias.get(indice_v);
                    
                    if (comparar(custo_v, menor_custo) < 0) {
                        menor_custo = custo_v;
                        atual = v;
                    }
                }
            }
            vertices_adj = verticesAdjacentes(atual);
        }

        ArrayList<Vertice> caminho = new ArrayList<>();
        Vertice fim = destino;

        while (fim != null) {
            caminho.add(fim);

            int indice = this.vertices.indexOf(fim);
            fim = antecessores.get(indice);
        }

        Collections.reverse(caminho);

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


    public static void main(String[] args) throws IOException {
        String arquivo = "labirinto.dat";
        int[][] matriz = matriz(arquivo);

        Grafo g = new Grafo();
        Vertice[][] mapa = new Vertice[matriz.length][matriz[0].length];

        // criando vertices
        for (int i = 0; i < matriz.length; i++) { 
            for (int j = 0; j < matriz[0].length; j++) { 
                
                    if (matriz[i][j] == 1) {
                        Vertice v = g.inserirVertice("{ " + i + ", " + j + " }");
                        v.setCoordenadaX(i);
                        v.setCoordenadaY(j);

                        mapa[i][j] = v;
                    }


            } 
        } 
        
        // conectando os vertices
        for (int i = 0; i < matriz.length; i++) { 
            for (int j = 0; j < matriz[0].length; j++) { 

                if (matriz[i][j] == 1) {
                    Vertice v = mapa[i][j];

                    // cima
                    if (i > 0 && matriz[i - 1][j] == 1) {
                        g.inserirAresta(v, mapa[i - 1][j], 1);
                    }

                    // baixo
                    if (i < matriz.length - 1 && matriz[i + 1][j] == 1) {
                        g.inserirAresta(v, mapa[i + 1][j], 1);
                    }

                    // esquerda
                    if (j > 0 && matriz[i][j - 1] == 1) {
                        g.inserirAresta(v, mapa[i][j - 1], 1);
                    }

                    // direita
                    if (j < matriz[0].length - 1 && matriz[i][j + 1] == 1) {
                        g.inserirAresta(v, mapa[i][j + 1], 1);
                    }
                }

            } 
        } 
        

    }

    public static int[][] matriz(String nomeArquivo) throws IOException { 
        List<String> linhas = Files.readAllLines(Paths.get(nomeArquivo)); 
        int n = linhas.size(); 
        int m = linhas.get(0).length(); 
        int[][] matriz = new int[n][m]; 
        for (int i = 0; i < n; i++) { 
            for (int j = 0; j < m; j++) { 
                matriz[i][j] = linhas.get(i).charAt(j) - '0'; 
            } 
        } 
        return matriz; 
    } 

}
