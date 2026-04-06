package Classes;

import Exceptions.PosicaoInvalida;

import java.awt.*;

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

        if (comparar(pai.get_element(), o) > 0){
            pai.set_filhoE(n);

            // Atualizar Fator de Balanceamento
            while (pai.get_FB() == 0){
                int fb = pai.get_FB() + 1;
                pai.set_FB(fb);

                System.out.println("Pai atual: " + pai.get_element() + " [" + pai.get_FB() + "]");

                if (isRoot(pai)){
                    break;
                }
                pai = pai.get_pai();
            }
        }
        else if (comparar(pai.get_element(), o) < 0){
            pai.set_filhoD(n);

            // Atualizar Fator de Balanceamento
            while (pai.get_FB() == 0){
                int fb = pai.get_FB() -1;
                pai.set_FB(fb);

                System.out.println("Pai atual: " + pai.get_element() + " [" + pai.get_FB() + "]");

                if (isRoot(pai)){
                    break;
                }
                pai = pai.get_pai();
            }
        }

        tamanho++;
        return n;
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
                System.out.print(matriz[i][j] + " ");
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
}
