package Tests;

import Classes.ArvoreAVL;
import Exceptions.ArvoreVazia;
import Exceptions.PosicaoInvalida;

import java.util.Scanner;
// import Classes.Node;

public class TesteArvoreAVL {
     static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Árvore AVL ===");
            System.out.print("Insira um número para criar a árvore raiz (ou 'R' para reiniciar): ");
            String entradaRaiz = scanner.next();

            if (entradaRaiz.equalsIgnoreCase("R")) {
                System.out.println("Reiniciando...");
                continue;
            }

            int raizVal;
            try {
                raizVal = Integer.parseInt(entradaRaiz);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                continue;
            }

            ArvoreAVL arvore = new ArvoreAVL(raizVal);

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
                            arvore.insercaoAVL(elemento);
                            System.out.println("Elemento " + elemento + " inserido.");
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida.");
                        } catch (PosicaoInvalida e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case "2":
                        System.out.print("Elemento para remover: ");
                        String entradaRemocao = scanner.next();
                        try {
                            int elemento = Integer.parseInt(entradaRemocao);
                            arvore.remover(elemento);
                            System.out.println("Elemento " + elemento + " removido.");
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida.");
                        } catch (ArvoreVazia | PosicaoInvalida e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    default:
                        System.out.println("Opção inválida. Digite 1, 2 ou R.");
                        break;
                }
            }
        }
    }
}
