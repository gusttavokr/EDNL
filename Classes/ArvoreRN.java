package Classes;

import Exceptions.ArvoreVazia;
import Exceptions.PosicaoInvalida;

import java.util.Scanner;

public class ArvoreRN extends ArvorePesquisa{

    public ArvoreRN(Object o){
        super(o);
    }

    public Node insert(Object o){

        // Inserção
        Node pai = busca(o, raiz);
        if (comparar(o, pai.get_element()) == 0){
            throw new PosicaoInvalida("Elemento já presente");
        }

        Node n = new Node(o);
        n.set_pai(pai);

        if (comparar(o, pai.get_element()) < 0){
            // Esquerda
            pai.set_filhoE(n);
            atualizarCor(n);

        } else if (comparar(o, pai.get_element()) > 0){
            // Direita
            pai.set_filhoD(n);
            atualizarCor(n);
        }

        tamanho++;

        return n;
    }

    public void atualizarCor(Node n){
        if (isRoot(n)){
            return;
        }

        Node pai = n.get_pai();
        Cor cor_pai = pai.get_Cor();

        if (n == leftChild(pai)){
            n.set_irmao(rightChild(pai));
        } else{
            n.set_irmao(leftChild(pai));
        }

        Node avo = pai.get_pai();
        Cor cor_avo = avo.get_Cor();

        Node tio = pai.get_irmao();
        Cor cor_tio = tio.get_Cor();


        // Caso 1
        if (cor_pai == Cor.NEGRO){
            n.set_Cor(Cor.RUBRO);
        }
        // Caso 2
        else if (cor_pai == Cor.RUBRO && cor_avo == Cor.NEGRO && cor_tio == Cor.RUBRO)  {
            avo.set_Cor(Cor.RUBRO);
            tio.set_Cor(Cor.NEGRO);
            pai.set_Cor(Cor.NEGRO);
        }
    }

    public void inOrder(Node n, String[][] matriz, int colunaAtual[]){

        if (hasLeft(n)) {
            inOrder(leftChild(n), matriz, colunaAtual);
        }

        int linha = depth(n);
        int coluna = colunaAtual[0]++;
        matriz[linha][coluna] = n.get_element().toString() + " [" + n.get_Cor() + "]";
        if (hasRight(n)) {
            inOrder(rightChild(n), matriz, colunaAtual);
        }
    }

    public void printArvore(){
        int linhas = height(raiz) + 1;
        int colunas = size();
        String[][] matriz = new String[linhas][colunas];

        for(int i = 0; i < linhas; i++){
            for (int j = 0; j < colunas; j++){
                matriz[i][j] = " ";
            }
        }

        int colunaAtual[] = new int[1];
        colunaAtual[0] = 0;

        inOrder(raiz, matriz, colunaAtual);

        for(int i = 0; i < linhas; i++){
            for (int j = 0; j < colunas; j++){
                System.out.printf("%-4s", matriz[i][j]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Árvore AVL ===");
            System.out.print("Insira um número para criar a árvore raiz: ");
            String entradaRaiz = scanner.next();



            int raizVal;
            try {
                raizVal = Integer.parseInt(entradaRaiz);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                continue;
            }

            ArvoreRN arvore = new ArvoreRN(raizVal);

            while (true) {
                arvore.printArvore();
                System.out.println("\nO que deseja fazer?");
                System.out.println("  1 - Inserir elemento");
                System.out.println("  2 - Remover elemento");
                System.out.println("  R - Reiniciar árvore");
                System.out.print("Opção: ");
                String opcao = scanner.next();

                if (opcao.equalsIgnoreCase("R")) {
                    System.out.println("Reiniciando...");
                    break;
                }

                switch (opcao) {
                    case "1":
                        System.out.print("Elemento para inserir: ");
                        String entradaInsercao = scanner.next();
                        try {
                            int elemento = Integer.parseInt(entradaInsercao);
                            arvore.insert(elemento);
                            System.out.println("Elemento " + elemento + " inserido.");
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida.");
                        } catch (PosicaoInvalida e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case "2":
                        System.out.println("Remoção em construção");
//                        System.out.print("Elemento para remover: ");
//                        String entradaRemocao = scanner.next();
//                        try {
//                            int elemento = Integer.parseInt(entradaRemocao);
//                            arvore.remover(elemento);
//                            System.out.println("Elemento " + elemento + " removido.");
//                        } catch (NumberFormatException e) {
//                            System.out.println("Entrada inválida.");
//                        } catch (ArvoreVazia | PosicaoInvalida e) {
//                            System.out.println("Erro: " + e.getMessage());
//                        }
//                        break;

                    default:
                        System.out.println("Opção inválida. Digite 1, 2 ou R.");
                        break;
                }
            }
        }
    }
}
