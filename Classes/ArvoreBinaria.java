package Classes;

import java.util.ArrayList;
import java.util.Iterator;

import Exceptions.ArvoreVazia;
import Exceptions.NodeSemFilho;
import Interfaces.TAD_ArvoreBinaria;

public class ArvoreBinaria extends ArvoreGenerica implements TAD_ArvoreBinaria {
    
    public ArvoreBinaria(Object o) {
        super(o);
    }

    public boolean hasLeft(Node n){
        if (n.get_filhoE() == null) {
            throw new NodeSemFilho("O nó não tem filho esquerdo");
        }
        return n.get_filhoE() != null;
    }
    public boolean hasRight(Node n){
        if (n.get_filhoD() == null) {
            throw new NodeSemFilho("O nó não tem filho direito");
        }
        return n.get_filhoD() != null;
    }

    public Node leftChild(Node n){
        if (isEmpty()) {
            throw new ArvoreVazia("A árvore está vazia");
        }
        return n.get_filhoE();
    }
    public Node rightChild(Node n){
        if (isEmpty()) {
            throw new ArvoreVazia("A árvore está vazia");
        }
        return n.get_filhoD();
    }

    public Node insertLeft(Object o, Node n){
        if (isEmpty()) {
            throw new ArvoreVazia("A árvore está vazia");
        }

        if (hasLeft(n)) {
            throw new NodeSemFilho("O nó já tem um filho esquerdo");
        }
        Node node = new Node(o);
        n.set_filhoE(node);
        node.set_pai(n);
        tamanho++;
        return node;
    }
    public Node insertRight(Object o, Node n){
        if (isEmpty()) {
            throw new ArvoreVazia("A árvore está vazia");
        }

        if (hasRight(n)) {
            throw new NodeSemFilho("O nó já tem um filho direito");
        }
        Node node = new Node(o);
        n.set_filhoD(node);
        node.set_pai(n);
        tamanho++;
        return node;
    }

    public Node insertRoot(Object o){
        if (root() != null) {
            throw new ArvoreVazia("A árvore já tem uma raiz");
        }

        Node root = new Node(o);
        this.raiz = root;
        tamanho = 1;
        return root;
    }

    @Override
    public boolean isExternal(Node n){
        if (isEmpty()) {
            throw new ArvoreVazia("A árvore está vazia");
        }

        return (!hasLeft(n) && !hasRight(n));
    }
    @Override
    public boolean isInternal(Node n){
        if (isEmpty()) {
            throw new ArvoreVazia("A árvore está vazia");
        }
        return (hasLeft(n) || hasRight(n));
    }

    public Object remove(Node n){
        if (isEmpty()) {
            throw new ArvoreVazia("A árvore está vazia");
        }
        Node pai = parent(n);
        Node filhoE = leftChild(n);
        Node filhoD = rightChild(n);

        // Remoção de nó com dois filhos.
        if (filhoE != null && filhoD != null) {
            if (isRoot(n)) {
                filhoE.set_pai(null);
            }
            if (leftChild(pai) == n) {
                pai.set_filhoE(filhoE); 
            } else if (rightChild(pai) == n){
                pai.set_filhoD(filhoE);
            }

            Node temp = filhoE;
            while (hasRight(temp)) {
                temp = temp.get_filhoD();
            }
            temp.set_filhoD(filhoD);    
            filhoD.set_pai(temp);
        }

        // Pelo menos 1 filho
        Node filho = null;

        if (filhoE != null) {
            filho = filhoE;
        } else if (filhoD != null){
            filho = filhoD;
        }

        // Se for raiz
        if (isRoot(n)) {
            raiz = filho;
            if (filho != null) {
                filho.set_pai(null);
            }
            filho.set_pai(null);
        } else{
            if (leftChild(pai) == n) {
                pai.set_filhoE(filho);
            } else if (rightChild(pai) == n){
                pai.set_filhoD(filho);
            }

            if (filho != null) {
                filho.set_pai(pai);
            }

        }

        // Caso o nó seja externo
        if (isExternal(n) || isExternal(n) && isRoot(n)){
            if (leftChild(pai) == n) {
                pai.set_filhoE(null);
                n.set_pai(null);
            } else {
                pai.set_filhoD(null);
                n.set_pai(null);
            }
        }

        tamanho--;
        return n.get_element();
    }

    @Override
    public int height(Node n){
        if (isEmpty()) {
            throw new ArvoreVazia("Árvore vazia");
        }

        if (isExternal(n)) {
            return 0;
        } 
        int h = 0;
        
        if (hasLeft(n)) {
            h = Math.max(h, height(leftChild(n)));
        }
        if (hasRight(n)) {
            h = Math.max(h, height(rightChild(n)));
        }
        return 1+h;
    }

    @Override
    public Iterator<Node> children (Node n){
        ArrayList<Node> array = new ArrayList<>();

        if (hasLeft(n)) {
            array.add(leftChild(n));
        }

        if(hasRight(n)){
            array.add(rightChild(n));
        }
        return array.iterator();
    }

    public void inOrder(Node n, String[][] matriz, int colunaAtual[]){

        if (hasLeft(n)) {
            inOrder(leftChild(n), matriz, colunaAtual);
        }

        int linha = depth(n);
        int coluna = colunaAtual[0]++;
        matriz[linha][coluna] = n.get_element().toString();

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

    @Override
    public void preOrder(Node n, ArrayList<Object> array){
        array.add(n.get_element());

        if (hasLeft(n)) {
            preOrder(leftChild(n), array);
        }
        
        if (hasRight(n)) {
            preOrder(rightChild(n), array);
        }
    }

    @Override
    public void postOrder(Node n, ArrayList<Node> array){
        if (hasLeft(n)) {
            postOrder(leftChild(n), array);
        }
        if (hasRight(n)) {
            postOrder(rightChild(n), array);
        }

        array.add(n);
    }
}