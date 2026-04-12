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

    public Node getIrmao(Node n) {
        Node pai = n.get_pai();

        if (n == pai.get_filhoE()) {
            return pai.get_filhoD();
        } else {
            return pai.get_filhoE();
        }
    }


    public Node insert(Object o){

        Node pai = busca(o, raiz);

        if (comparar(o, pai.get_element()) == 0){
            throw new PosicaoInvalida("Elemento já presente");
        }


        // Inserção

        Node n = new Node(o);
        n.set_Cor(Cor.RUBRO);
        n.set_pai(pai);


        if (comparar(o, pai.get_element()) < 0){
            // Esquerda
            pai.set_filhoE(n);
        } else{
            // Direita
            pai.set_filhoD(n);   
        }
        atualizarCor(n);

        
        raiz.set_Cor(Cor.NEGRO);
        tamanho++;
        return n;
    }

    public void atualizarCor(Node n){

        if (isRoot(n)) {
            n.set_Cor(Cor.NEGRO);
        }

        Node pai = n.get_pai();
        
        // Se o pai for NEGRO, nem faz nada
        if (cor(pai) == Cor.RUBRO) {
            Node avo = pai.get_pai();
            Node tio;

            if (pai == leftChild(avo)) {
                tio = rightChild(avo);
            } else{
                tio = leftChild(avo);
            }

            // Se o Tio For Rubro
            if (cor(tio) == Cor.RUBRO) {
                pai.set_Cor(Cor.NEGRO);
                tio.set_Cor(Cor.NEGRO);
                avo.set_Cor(Cor.RUBRO);
                atualizarCor(avo);
            } else {
                // Se o Tio For Negro, precisa de rotação e colorir
                rotacao(n);
            }

            raiz.set_Cor(Cor.NEGRO);
        }
    }

    public void rotacao(Node n){
        Node pai = n.get_pai();
        Node avo = pai.get_pai();

        if (pai == rightChild(avo) && n == rightChild(pai)) {
            RotacaoSimplesEsquerda(avo);
            pai.set_Cor(Cor.NEGRO);
            avo.set_Cor(Cor.RUBRO);
        }
        else if (pai == leftChild(avo) && n == leftChild(pai)) {
            RotacaoSimplesDireita(avo);
            pai.set_Cor(Cor.NEGRO);
            avo.set_Cor(Cor.RUBRO);
        }
        else if (pai == rightChild(avo) && n == leftChild(pai)) {
            RotacaoSimplesDireita(pai);
            RotacaoSimplesEsquerda(avo);
            n.set_Cor(Cor.NEGRO);
            avo.set_Cor(Cor.RUBRO);
        }
        else if (pai == leftChild(avo) && n == rightChild(pai)) {
            RotacaoSimplesEsquerda(pai);
            RotacaoSimplesDireita(avo);
            n.set_Cor(Cor.NEGRO);
            avo.set_Cor(Cor.RUBRO);
        }
    }

    public void RotacaoSimplesEsquerda(Node n){

        // Rotação Esquerda
        Node filhoD = n.get_filhoD();
        Node oldPai = n.get_pai();
        Node sucessor = filhoD.get_filhoE();
        
        filhoD.set_pai(oldPai);
        if (oldPai != null) {
            if (oldPai.get_filhoD() == n) {
                oldPai.set_filhoD(filhoD);
            } else {
                oldPai.set_filhoE(filhoD);
            }
        } else if (isRoot(n)){
            raiz = filhoD;
        }
        
        filhoD.set_filhoE(n);
        n.set_filhoD(sucessor);
        
        if (sucessor != null) {
            sucessor.set_pai(n);
        }
        
        n.set_pai(filhoD);
    }
    public void RotacaoSimplesDireita(Node n){

        // Rotação Esquerda
        Node filhoE = n.get_filhoE();
        Node oldPai = n.get_pai();
        Node sucessor = filhoE.get_filhoD();
        
        filhoE.set_pai(oldPai);
        if (oldPai != null) {
            if (oldPai.get_filhoE() == n) {
                oldPai.set_filhoE(filhoE);
            } else {
                oldPai.set_filhoD(filhoE);
            }
        } else if (isRoot(n)){
            raiz = filhoE;
        }
        
        filhoE.set_filhoD(n);
        n.set_filhoE(sucessor);
        
        if (sucessor != null) {
            sucessor.set_pai(n);
        }
        
        n.set_pai(filhoE);
    }

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
