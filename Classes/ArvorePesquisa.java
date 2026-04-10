package Classes;

import Exceptions.ArvoreVazia;
import Exceptions.PosicaoInvalida;

public class ArvorePesquisa extends ArvoreBinaria {
    public ArvorePesquisa(Object o){
        super(o);
    }

    public Node busca(Object o, Node n){

        if (comparar(o, n.get_element()) < 0) {
            if (hasLeft(n)) {
                return busca(o, leftChild(n));
            }
        }
        
        else if ((comparar(o, n.get_element())) == 0){
            return n;
        }

        else if (comparar(o, n.get_element()) > 0) {
            if (hasRight(n)) {
                return busca(o, rightChild(n));
            }
        }
        
        return n;
    }

    public int comparar(Object o, Object p) {
        int oInt = converterInt(o);
        int pInt = converterInt(p);

        return Integer.compare(oInt, pInt);
    }

    public int converterInt(Object p) {

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

    public Node insert(Object o){
        if (isEmpty()) {
            insertRoot(o);
            return root();
        }
        
        Node pai = busca(o, raiz);
        if (comparar(o, pai.get_element()) == 0) {
            throw new PosicaoInvalida ("Elemento presente na árvore");
        }
        
        Node n = new Node(o);
        n.set_pai(pai);

        if (comparar(pai.get_element(), o) > 0) {
            pai.set_filhoE(n);
        }
        else if (comparar(pai.get_element(), o) < 0){
            pai.set_filhoD(n);
        }

        tamanho++;
        return n;
    }

    public Node insert2(Object o){

            if (isEmpty()) {
                insertRoot(o);
                return root();
            }

            Node raiz = root();

            if ((comparar(o, raiz)) == 0) {
                throw new PosicaoInvalida ("Elemento presente na árvore");
            }

            Node temp = raiz;
            Node filho = null;

            while (!isExternal(temp)) {
                if (comparar(o, temp.get_element()) < 0) {
                    if (!hasLeft(temp)) {
                        break;
                    } else{
                        temp = leftChild(temp);
                    }
                }
                else if (comparar(o, temp.get_element()) > 0) {
                    if (!hasRight(temp)) {
                        break;
                    } else{
                        temp = rightChild(temp);
                    }
                }
            }

            if (comparar(o, temp.get_element()) > 0) {
                System.out.println("Inserindo o: " + o + " em filho direito");
                insertRight(o, temp);
                filho = rightChild(temp);
            }
            
            else if (comparar(o, temp.get_element()) < 0) {
                System.out.println("Inserindo o: " + o + " em filho esquerdo");
                insertLeft(o, temp);
                filho = leftChild(temp);
            }

            return filho;
    }

    public Object remove(Node n){
        if (isEmpty()) {
            throw new ArvoreVazia("A árvore está vazia brow");
        }

        Object element = n.get_element();
        
        Node pai = parent(n);
        Node filho;

        if (isExternal(n)) {
            if (isRoot(n)){
                System.out.println("Cheguei aqui");
                raiz = null;
                tamanho = 0;
            }
            else if (leftChild(pai) == n) {
                pai.set_filhoE(null);
                tamanho--;
            }
            else if (rightChild(pai) == n){
                pai.set_filhoD(null);
                tamanho--;
            }
            return element;
        }

        if (hasLeft(n) && hasRight(n)) {
            filho = rightChild(n);
            
            while (isExternal(n)) {
                filho = leftChild(filho);
            }
            
            Object elementoFilho = filho.get_element();
            replace(n, elementoFilho);

            pai = filho.get_pai();
            if (filho == pai.get_filhoD()) {
                pai.set_filhoD(null);
            } else if (filho == pai.get_filhoE()){
                pai.set_filhoE(filho);
            }

            return element;
        }
        else if (hasLeft(n) && !hasRight(n)) {
            filho = leftChild(n);
            if (isRoot(n)){
                filho.set_pai(null);
                raiz = filho;
                tamanho--;
                return element;
            }
            else if (leftChild(pai) == n) {
                pai.set_filhoE(filho);
            } else if (rightChild(pai) == n){
                pai.set_filhoD(filho);
            }
            filho.set_pai(pai);
        } else if(!hasLeft(n) && hasRight(n)){
            filho = rightChild(n);
            if (leftChild(pai) == n) {
                pai.set_filhoE(filho);
            } else if(rightChild(pai) == n){
                pai.set_filhoD(filho);
            }
            filho.set_pai(pai);
        }

        tamanho--;
        return element;

    }
}
