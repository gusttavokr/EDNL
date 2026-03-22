package Classes;

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

        return (n.get_filhoE() == null && n.get_filhoD() == null);
    }

    @Override
    public boolean isInternal(Node n){
        if (isEmpty()) {
            throw new ArvoreVazia("A árvore está vazia");
        }

        return (n.get_filhoE() != null || n.get_filhoD() != null);
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
}