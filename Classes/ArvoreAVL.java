package Classes;

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

                if (p.get_FB() > 1){
                    desbalanceado = p;
                }

                p = p.get_pai();

            } else if (comparar(n.get_element(), p.get_element()) > 0) {
                p.set_FB(p.get_FB()-1);

                if (p.get_FB() < -1){
                    desbalanceado = p;
                    break;
                }

                p = p.get_pai();
            }
        }


//        if (converterInt(n.get_element()) == 6){
//            System.out.println("Deu certo");
//            return;
//        }

        if (desbalanceado != null){
            rotacao(desbalanceado);
        }
    }

    public void rotacao(Node n){

        Node filhoD = n.get_filhoD();
        Node filhoE = n.get_filhoE();

        Node oldPai = n.get_pai();

        if (n.get_FB() == 2){

            // Rotação Direita
            Node antecessor = filhoE.get_filhoD();

            filhoE.set_pai(oldPai);

            if (oldPai != null){
                oldPai.set_filhoD(filhoE);
            }
            if (isRoot(n)){
                raiz = filhoE;
            }

            filhoE.set_filhoD(n);
            n.set_filhoE(antecessor);
            n.set_pai(filhoE);

            n.set_FB(n.get_FB() - 1 - Math.max(filhoE.get_FB(), 0));
            filhoE.set_FB(filhoE.get_FB() - 1 + Math.min(n.get_FB(), 0));
        }

        if (n.get_FB() == -2){
            // Rotação Esquerda

            Node sucessor = filhoD.get_filhoE();

            filhoD.set_pai(oldPai);
            if (oldPai != null) {
                oldPai.set_filhoD(filhoD);
            } else {
                raiz = filhoD;
            }

            filhoD.set_filhoE(n);
            n.set_filhoD(sucessor);
            n.set_pai(filhoD);

            n.set_FB(n.get_FB() + 1 - Math.min(filhoD.get_FB(), 0));
            filhoD.set_FB(filhoD.get_FB() + 1 + Math.max(n.get_FB(), 0));
        }

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
}
