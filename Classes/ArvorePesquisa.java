package Classes;

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
            n.set_pai(pai);
        }
        else if (comparar(pai.get_element(), o) < 0){
            pai.set_filhoD(n);
            n.set_pai(pai);
        }

        tamanho++;
        return n;
    }

    // public Node insert(Object o){

    //         if (isEmpty()) {
    //             insertRoot(o);
    //             return root();
    //         }

    //         Node raiz = root();

    //         if ((comparar(o, raiz)) == 0) {
    //             throw new PosicaoInvalida ("Elemento presente na árvore");
    //         }

    //         Node temp = raiz;
    //         Node filho = null;

    //         while (!isExternal(temp)) {
    //             if (comparar(temp.get_element(), o) < 0) {
    //                 if (!hasLeft(temp)) {
    //                     break;
    //                 } else{
    //                     temp = leftChild(temp);
    //                 }
    //             }
    //             else if (comparar(temp.get_element(), o) > 0) {
    //                 if (!hasRight(temp)) {
    //                     break;
    //                 } else{
    //                     temp = rightChild(temp);
    //                 }
    //             }
    //         }

    //         if (comparar(temp.get_element(), o) > 0) {
    //             System.out.println("Inserindo o: " + o + " em filho direito");
    //             insertRight(o, temp);
    //             filho = rightChild(temp);
    //         }
            
    //         else if (comparar(temp.get_element(), o) < 0) {
    //             System.out.println("Inserindo o: " + o + " em filho esquerdo");
    //             insertLeft(o, temp);
    //             filho = leftChild(temp);
    //         }

    //         return filho;
    // }
}
