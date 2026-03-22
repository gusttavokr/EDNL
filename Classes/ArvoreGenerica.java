package Classes;

import java.util.ArrayList;
import java.util.Iterator;

import Exceptions.ArvoreVazia;
import Exceptions.NodeSemFilho;
import Interfaces.TAD_ArvoreGenerica;

public class ArvoreGenerica implements TAD_ArvoreGenerica {
    public int tamanho;
    public Node raiz;

    public ArvoreGenerica(Object o){
        this.raiz = new Node(o);
        this.tamanho = 1;
    }

    public int size(){
        return this.tamanho;
    }

    public boolean isRoot(Node n){
        return (n == raiz);
    }

    public Node root(){
        if (isEmpty()) {
            throw new ArvoreVazia("arvore vazia");
        }
        return this.raiz;
    }

    public Node parent(Node n){
        if (isEmpty()) {
            throw new ArvoreVazia("arvore vazia");
        }
        return n.get_pai();
    }

    public int depth(Node n){
        if (isRoot(n)) {
            return 0;
        }
        return 1 + depth(parent(n));
    }

    public boolean isExternal(Node n){
        return (n.get_filhos().size() == 0);
    }

    public boolean isInternal(Node n){
        return (n.get_filhos().size() != 0);
    }

    public int height(Node n){
        if (isExternal(n)) {
            return 0;
        } else {
            int h = 0;
            for(Node w : n.get_filhos()){
                h = Math.max(h, height(w));
            }
            return h + 1;
        }
    }

    public boolean isEmpty(){
        return this.tamanho == 0;
    }

    public Iterator<Node> children(Node n){
        if (isExternal(n)) {
            throw new NodeSemFilho("Não tem filhos.");
        }
        return n.get_filhos().iterator();
    }

    public Iterator<Object> elements(){
        if (isEmpty()) {
            throw new ArvoreVazia("arvore vazia");
        }
        ArrayList<Object> array = new ArrayList<>();
        preOrder(raiz, array);
        return array.iterator();
    }

    // Prioriza o pai na travessia
    public void preOrder(Node n, ArrayList<Object> array){
        Object o = n.get_element();
        array.add(o);
        for(Node w : n.get_filhos()){
            preOrder(w, array);
        }
    }

    public Iterator<Node> nodes(){
        if (isEmpty()) {
            throw new ArvoreVazia("arvore vazia");
        }

        ArrayList<Node> array = new ArrayList<>();
        postOrder(raiz, array);
        return array.iterator();
    }

    // Prioriza os filhos na travessia
    public void postOrder(Node n, ArrayList<Node> array){
        for (Node w : n.get_filhos()){
            postOrder(w, array);
        }
        array.add(n);
    }


    public Object replace(Node n, Object o){
        if (isEmpty()) {
            throw new ArvoreVazia("arvore vazia");
        }
        Object temp = n.get_element();
        n.set_element(o);
        return temp;
    }
}
