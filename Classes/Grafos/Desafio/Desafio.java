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
        String arquivo = "labirinto.dat";
        int[][] matriz = matriz(arquivo);

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
                    if (i > 0 && matriz[i - 1][j] == 0 
                        || i > 0 && matriz[i - 1][j] == 2 
                        || i > 0 && matriz[i - 1][j] == 3) {
                        d.inserirAresta(v, mapa[i - 1][j], 1);
                    }

                    // baixo
                    if (i < matriz.length - 1 && matriz[i + 1][j] == 0 
                        || matriz[i + 1][j] == 2 
                        || matriz[i + 1][j] == 3) {
                        d.inserirAresta(v, mapa[i + 1][j], 1);
                    }

                    // esquerda
                    if (j > 0 && matriz[i][j - 1] == 0 
                        || matriz[i][j - 1] == 2 
                        || matriz[i][j - 1] == 3) {
                        d.inserirAresta(v, mapa[i][j - 1], 1);
                    }

                    // direita
                    if (j < matriz[0].length - 1 && matriz[i][j + 1] == 0 
                        || matriz[i][j + 1] == 2 
                        || matriz[i][j + 1] == 3) {
                        d.inserirAresta(v, mapa[i][j + 1], 1);
                    }
                }

            } 
        } 

        // identificar origem e destino
        Vertice origem;
        Vertice destino;

        for (int i = 0; i < matriz.length; i++) { 
            for (int j = 0; j < matriz[0].length; j++) { 
                if (matriz[i][j] == 2) {
                    origem = mapa[i][j];
                } else if (matriz[i][j] == 3){
                    destino = mapa[i][j];
                }
            }
        }

        ArrayList<Vertice> resultadoDijkstra = d.dijkstra(origem, destino);
        

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
