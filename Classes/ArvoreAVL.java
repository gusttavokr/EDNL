package Classes;

import java.util.Scanner;

import Exceptions.ArvoreVazia;
import Exceptions.PosicaoInvalida;

public class ArvoreAVL extends ArvorePesquisa {
    public ArvoreAVL(Object o){
        super(o);
    }

    public Node insercaoAVL(Object o){

        // Inserção
        Node pai = busca(o, raiz);
        if (comparar(o, pai.get_element()) == 0){
            throw new PosicaoInvalida("Elemento já presente");
        }

        Node n = new Node(o);
        n.set_pai(pai);

        // Atualizando ponteiro + FB

        if (comparar(o, pai.get_element()) < 0) {
            pai.set_filhoE(n);
            atualizarFB(n, pai);
        }
        else if (comparar(o, pai.get_element()) > 0) {
            pai.set_filhoD(n);
            atualizarFB(n, pai);
        }

        tamanho++;
        return n;
    }

    public void atualizarFB(Node n, Node p){

        Node desbalanceado = null;

        while (p != null) {
            if (comparar(n.get_element(), p.get_element()) < 0) {
                p.set_FB(p.get_FB()+1);
            } else if (comparar(n.get_element(), p.get_element()) > 0) {
                p.set_FB(p.get_FB()-1);
            }

            if (p.get_FB() == 0) {
                break;
            }

            if (p.get_FB() > 1 || p.get_FB() < -1){
                desbalanceado = p;
                break;
            }

            p = p.get_pai();
        }


        if (desbalanceado != null){
            rotacao(desbalanceado);
        }
    }

    public void rotacao(Node n){

        if (n.get_FB() >= 2) {
            if (hasLeft(n)) {
                Node filhoE = n.get_filhoE();
                if (filhoE.get_FB() >= 0) {
                    rotacaoSimplesDireita(n);
                } else {
                    rotacaoSimplesEsquerda(filhoE);
                    rotacaoSimplesDireita(n);
                }
            }
        } else if (n.get_FB() <= -2) {
            if (hasRight(n)){
                Node filhoD = n.get_filhoD();
                if (filhoD.get_FB() <= 0){
                    rotacaoSimplesEsquerda(n);
                } else{
                    rotacaoSimplesDireita(filhoD);
                    rotacaoSimplesEsquerda(n);
                }
            }
        }
    }

    public void rotacaoSimplesDireita(Node n){
        Node filhoE = n.get_filhoE();
        Node oldPai = n.get_pai();

        Node antecessor = filhoE.get_filhoD();

        filhoE.set_pai(oldPai);

        if (oldPai != null){
            if (oldPai.get_filhoE() == n) {
                oldPai.set_filhoE(filhoE);
            } else{
                oldPai.set_filhoD(filhoE);
            }
        } else if (isRoot(n)){
            raiz = filhoE;
        }

        filhoE.set_filhoD(n);
        n.set_filhoE(antecessor);

        if (antecessor != null) {
            antecessor.set_pai(n);
        }

        n.set_pai(filhoE);

        n.set_FB(n.get_FB() - 1 - Math.max(filhoE.get_FB(), 0));
        filhoE.set_FB(filhoE.get_FB() - 1 + Math.min(n.get_FB(), 0));
    }

    public void rotacaoSimplesEsquerda(Node n){
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

        n.set_FB(n.get_FB() + 1 - Math.min(filhoD.get_FB(), 0));
        filhoD.set_FB(filhoD.get_FB() + 1 + Math.max(n.get_FB(), 0));
    }

    public void inOrder(Node n, String[][] matriz, int colunaAtual[]){

        if (hasLeft(n)) {
            inOrder(leftChild(n), matriz, colunaAtual);
        }

        int linha = depth(n);
        int coluna = colunaAtual[0]++;
        matriz[linha][coluna] = n.get_element().toString() + " [" + n.get_FB() + "]";
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

    private int comparar(Object o, Object p) {
        int oInt = converterInt(o);
        int pInt = converterInt(p);

        return Integer.compare(oInt, pInt);
    }

    private int converterInt(Object p) {

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

    public Node remover(Object o){
        if (isEmpty()){
            throw new ArvoreVazia("A árvore está vazia");
        }

        Node removido = busca(o, raiz);

        if (removido.get_element() != o){
            throw new PosicaoInvalida("Esse elemento não está na árvore");
        }

        if (isRoot(removido)){
            raiz = null;
            tamanho --;
            return removido;
        } else{
            Node pai = removido.get_pai();

            if (comparar(o, pai.get_element()) < 0) {
                pai.set_filhoE(null);
                removido.set_pai(null);
                atualizarFBRemocao(removido, pai);

                if (isExternal(removido)){
                    return removido;
                }

                Node filhoD = removido.get_filhoD();
                Node filhoE = removido.get_filhoE();
                filhoD.set_pai(pai);
                pai.set_filhoE(filhoD);

                filhoE.set_pai(filhoD);
                filhoD.set_filhoE(filhoE);

            }
            else if (comparar(o, pai.get_element()) > 0) {
                pai.set_filhoD(null);
                removido.set_pai(null);
                atualizarFBRemocao(removido, pai);

                if (isExternal(removido)){
                    return removido;
                }

                Node filhoD = removido.get_filhoD();
                Node filhoE = removido.get_filhoE();
                filhoE.set_pai(pai);
                pai.set_filhoD(filhoE);

                filhoD.set_pai(filhoE);
                filhoE.set_filhoE(filhoD);
            }
        }

        return removido;
    }

    public void atualizarFBRemocao(Node n, Node p){

        Node desbalanceado = null;

        while (p != null) {
            if (comparar(n.get_element(), p.get_element()) < 0) {
                p.set_FB(p.get_FB()-1);
            } else if (comparar(n.get_element(), p.get_element()) > 0) {
                p.set_FB(p.get_FB()+1);
            }



            if (p.get_FB() > 1 || p.get_FB() < -1){
                desbalanceado = p;
                break;
            }

            p = p.get_pai();
        }


        if (desbalanceado != null){
            rotacao(desbalanceado);
        }
    }

    static void main(String[] args) {
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
