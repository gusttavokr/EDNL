package Tests;

import Classes.Node;
import Classes.Arvores.ArvoreBinaria;

public class TesteArvoreBinaria {
    public static void main(String[] args) {
        
        ArvoreBinaria arvore = new ArvoreBinaria("1");

        Node raiz = arvore.root();

        arvore.insertLeft("2", raiz);
        arvore.insertRight("3", raiz);

        Node filho1 = arvore.leftChild(raiz);
        // Node filho2 = arvore.rightChild(raiz);

        arvore.printArvore();

        System.out.println("Removendo o 2");
        
        arvore.remove(filho1);
        arvore.printArvore();
    }
}
