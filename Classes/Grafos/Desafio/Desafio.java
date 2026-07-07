package Classes.Grafos.Desafio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Classes.Grafos.Grafo;
import Classes.Grafos.Vertice;

public class Desafio extends Grafo {

    public static void main(String[] args) throws IOException {
        String arquivo = "C:\\Users\\gusta\\Downloads\\EDNL\\Classes\\Grafos\\Desafio\\labirinto.dat";
        int[][] matriz = matriz(arquivo);
        imprimirMatriz(matriz);

        Desafio d = new Desafio();

        Vertice[][] mapa = new Vertice[matriz.length][matriz[0].length];

        // criando vertices
        for (int i = 0; i < matriz.length; i++) { 
            for (int j = 0; j < matriz[0].length; j++) { 
                
                    if (matriz[i][j] == 0 || matriz[i][j] == 2 || matriz[i][j] == 3) {
                        Vertice v = d.inserirVertice("{ " + i + ", " + j + " }");
                        v.setCoordenadaX(i);
                        v.setCoordenadaY(j);

                        mapa[i][j] = v;
                    }


            } 
        } 
        
        // conectando os vertices
        for (int i = 0; i < matriz.length; i++) { 
            for (int j = 0; j < matriz[0].length; j++) { 

                if (matriz[i][j] == 0 || matriz[i][j] == 2 || matriz[i][j] == 3) {
                    Vertice v = mapa[i][j];

                    // cima
                    if (i > 0 && (matriz[i - 1][j] == 0 
                        || i > 0 && matriz[i - 1][j] == 2 
                        || i > 0 && matriz[i - 1][j] == 3)) {
                        d.inserirAresta(v, mapa[i - 1][j], 1);
                    }

                    // baixo
                    if (i < matriz.length - 1 && (matriz[i + 1][j] == 0 
                        || matriz[i + 1][j] == 2 
                        || matriz[i + 1][j] == 3)) {
                        d.inserirAresta(v, mapa[i + 1][j], 1);
                    }

                    // esquerda
                    if (j > 0 && (matriz[i][j - 1] == 0 
                        || matriz[i][j - 1] == 2 
                        || matriz[i][j - 1] == 3)) {
                        d.inserirAresta(v, mapa[i][j - 1], 1);
                    }

                    // direita
                    if (j < matriz[0].length - 1 && (matriz[i][j + 1] == 0 
                        || matriz[i][j + 1] == 2 
                        || matriz[i][j + 1] == 3)) {
                        d.inserirAresta(v, mapa[i][j + 1], 1);
                    }
                }

            } 
        } 

        // identificar origem e destino
        Vertice origem = null;
        Vertice destino = null;

        for (int i = 0; i < matriz.length; i++) { 
            for (int j = 0; j < matriz[0].length; j++) { 
                if (matriz[i][j] == 2) {
                    origem = mapa[i][j];
                } else if (matriz[i][j] == 3){
                    destino = mapa[i][j];
                }
            }
        }

        
        if (origem == null || destino == null) {
            System.out.println("Origem ou destino não encontrados no labirinto.");
            return;
        }

        System.out.println("Origem: " + origem.getElement());
        System.out.println("Destino: " + destino.getElement());
        System.out.println();

        long inicioDijkstra = System.nanoTime();
        ArrayList<Vertice> caminhoDijkstra = d.dijkstra(origem, destino);
        long fimDijkstra = System.nanoTime();

        imprimirResultado("Dijkstra", caminhoDijkstra, (fimDijkstra - inicioDijkstra)/1_000_000.0);

        long inicioAStar = System.nanoTime();
        ArrayList<Vertice> caminhoAStar = d.the_star(origem, destino);
        long fimAStar = System.nanoTime();

        imprimirResultado("A*", caminhoAStar, (fimAStar - inicioAStar)/1_000_000.0);
    }

    public static void imprimirResultado(String algoritmo, ArrayList<Vertice> caminho, double tempo) {
        System.out.println("=== " + algoritmo + " ===");
        if (caminho == null || caminho.isEmpty()) {
            System.out.println("Nenhum caminho encontrado.");
        } else {
            for (Vertice v : caminho) {
                System.out.print(v.getElement() + " -> ");
            }
            System.out.println("FIM");
            System.out.println("Número de passos: " + (caminho.size() - 1));
        }
        System.out.println("Tempo: " + tempo + " ms");
        System.out.println();
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

    public static void imprimirMatriz(int[][] matriz) {
        System.out.println("Matriz do labirinto:");
        for (int[] linha : matriz) {
            for (int valor : linha) {
                System.out.print(valor);
            }
            System.out.println();
        }
        System.out.println();
    }
}
