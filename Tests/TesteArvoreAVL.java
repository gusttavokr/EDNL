package Tests;

import Classes.ArvoreAVL;

public class TesteArvoreAVL {
    public static void main(String[] args) {
        System.out.println("Testes para a Arvore AVL");

        ArvoreAVL arvore = new ArvoreAVL(1);
//        arvore.insercaoAVL(2);
        arvore.printArvore();
    }
}
