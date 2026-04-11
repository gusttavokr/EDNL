package Classes;

import Exceptions.ArvoreVazia;
import Exceptions.PosicaoInvalida;

import java.util.Scanner;

public class ArvoreRN extends ArvorePesquisa{
    public static final String ANSI_RED = "\u001B[38;2;255;0m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    public ArvoreRN(Object o){
        super(o);
    }

    public Cor cor(Node n){
        if (n == null) {
            return Cor.NEGRO;
        }
        return n.get_Cor();
    }


    public Node insert(Object o){

        Node pai = busca(o, raiz);

        if (comparar(o, pai.get_element()) == 0){
            throw new PosicaoInvalida("Elemento já presente");
        }

        Node n = new Node(o);
        n.set_pai(pai);

        if (comparar(o, pai.get_element()) < 0){
            // Esquerda
            pai.set_filhoE(n);
            if (hasRight(pai)) {
                n.set_irmao(rightChild(pai));
            }
            atualizarCor(n);
            
        } else if (comparar(o, pai.get_element()) > 0){
            // Direita
            pai.set_filhoD(n);
            if (hasLeft(pai)) {
                n.set_irmao(leftChild(pai));
            }
            atualizarCor(n);
        }


        tamanho++;

        return n;
    }

    public void atualizarCor(Node n){
        if (isExternal(n)) {
            n.set_Cor(Cor.RUBRO);
        }

        Node pai = n.get_pai();
        Node tio = pai.get_irmao();
        // Cor cor_tio = (pai.get_irmao()).get_Cor();
        if (cor(pai) == Cor.RUBRO) {
            pai.set_Cor(Cor.NEGRO);
            tio.set_Cor(Cor.NEGRO);
        }
    }

    // public void atualizarCor(Node n){
    //     if (isRoot(n)){
    //         return;
    //     }

    //     Node pai = n.get_pai();
    //     Cor cor_pai = pai.get_Cor();

    //     if (n == leftChild(pai)){
    //         n.set_irmao(rightChild(pai));
    //     } else{
    //         n.set_irmao(leftChild(pai));
    //     }

    //     // Caso 1
    //     if (cor_pai == Cor.NEGRO){
    //         n.set_Cor(Cor.RUBRO);
    //         return;
    //     }

    //     Node avo = pai.get_pai();
    //     Cor cor_avo = avo.get_Cor();

    //     Node tio = pai.get_irmao();
    //     Cor cor_tio = tio.get_Cor();

    //     // Caso 2
    //     if (cor_pai == Cor.RUBRO && cor_avo == Cor.NEGRO && cor_tio == Cor.RUBRO)  {
    //         tio.set_Cor(Cor.NEGRO);
    //         pai.set_Cor(Cor.NEGRO);
    //         if (!isRoot(avo)) {
    //             avo.set_Cor(Cor.RUBRO);
    //             return;
    //         }
    //         if (avo.get_pai() != null && (avo.get_pai()).get_Cor() == Cor.RUBRO) {
    //             atualizarCor(avo);            
    //             return;            
    //         }
    //         atualizarCor(n);
    //         return;            
    //     }

    //     if (cor_pai == Cor.RUBRO && cor_avo == Cor.NEGRO && cor_tio == Cor.NEGRO) {
    //         rotacao(n);
    //         return;
    //     }
    // }

    // public void rotacao(Node n){
    //     Node pai = n.get_pai();
    //     Node avo = pai.get_pai();
    //     Node tio = pai.get_irmao();
    //     Node irmao = n.get_irmao();

    //     if (leftChild(avo) == pai) {
    //         // Rotação Simples Direita
    //         avo.set_pai(pai);
    //         pai.set_filhoD(avo);
    //         avo.set_filhoE(irmao);
    //     } else if (rightChild(avo) == pai){
    //         // Rotação Simples Esquerda
    //     }

    //     avo.set_pai(pai);
    //     pai.set_filhoD(tio);
    // }


    // public void rota(Node n){
    //     if (n.get_FB() >= 2) {
    //         if (hasLeft(n)) {
    //             Node filhoE = n.get_filhoE();
    //             if (filhoE.get_FB() >= 0) {
    //                 rotacaoSimplesDireita(n);
    //             } else {
    //                 rotacaoSimplesEsquerda(filhoE);
    //                 rotacaoSimplesDireita(n);
    //             }
    //         }
    //     } else if (n.get_FB() <= -2) {
    //         if (hasRight(n)){
    //             Node filhoD = n.get_filhoD();
    //             if (filhoD.get_FB() <= 0){
    //                 rotacaoSimplesEsquerda(n);
    //             } else{
    //                 rotacaoSimplesDireita(filhoD);
    //                 rotacaoSimplesEsquerda(n);
    //             }
    //         }
    //     }
    // }

    // public void rotacaoSimplesDireita(Node n){
    //     Node filhoE = n.get_filhoE();
    //     Node oldPai = n.get_pai();

    //     Node antecessor = filhoE.get_filhoD();

    //     filhoE.set_pai(oldPai);

    //     if (oldPai != null){
    //         if (oldPai.get_filhoE() == n) {
    //             oldPai.set_filhoE(filhoE);
    //         } else{
    //             oldPai.set_filhoD(filhoE);
    //         }
    //     } else if (isRoot(n)){
    //         raiz = filhoE;
    //     }

    //     filhoE.set_filhoD(n);
    //     n.set_filhoE(antecessor);

    //     if (antecessor != null) {
    //         antecessor.set_pai(n);
    //     }

    //     n.set_pai(filhoE);

    //     n.set_FB(n.get_FB() - 1 - Math.max(filhoE.get_FB(), 0));
    //     filhoE.set_FB(filhoE.get_FB() - 1 + Math.min(n.get_FB(), 0));
    // }

    // public void rotacaoSimplesEsquerda(Node n){
    //     // Rotação Esquerda
    //     Node filhoD = n.get_filhoD();
    //     Node oldPai = n.get_pai();

    //     Node sucessor = filhoD.get_filhoE();

    //     filhoD.set_pai(oldPai);
    //     if (oldPai != null) {
    //         if (oldPai.get_filhoD() == n) {
    //             oldPai.set_filhoD(filhoD);
    //         } else {
    //             oldPai.set_filhoE(filhoD);
    //         }
    //     } else if (isRoot(n)){
    //         raiz = filhoD;
    //     }

    //     filhoD.set_filhoE(n);
    //     n.set_filhoD(sucessor);

    //     if (sucessor != null) {
    //         sucessor.set_pai(n);
    //     }

    //     n.set_pai(filhoD);

    //     n.set_FB(n.get_FB() + 1 - Math.min(filhoD.get_FB(), 0));
    //     filhoD.set_FB(filhoD.get_FB() + 1 + Math.max(n.get_FB(), 0));
    // }

    public void inOrder(Node n, String[][] matriz, int colunaAtual[]){

        if (hasLeft(n)) {
            inOrder(leftChild(n), matriz, colunaAtual);
        }

        int linha = depth(n);
        int coluna = colunaAtual[0]++;

        String textoDoNo = String.format("%-2s", n.get_element().toString());

        if (cor(n) == Cor.RUBRO) {
            matriz[linha][coluna] = ANSI_RED + textoDoNo + ANSI_RESET;
        } else {
            matriz[linha][coluna] = textoDoNo; 
        }

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
                matriz[i][j] = "  ";
            }
        }

        int colunaAtual[] = new int[1];
        colunaAtual[0] = 0;

        inOrder(raiz, matriz, colunaAtual);

        for(int i = 0; i < linhas; i++){
            for (int j = 0; j < colunas; j++){
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Árvore Rubro-Negra ===");
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
