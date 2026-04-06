package Tests;

import Classes.ArvoreAVL;

public class TesteArvoreAVL {
    public static void main(String[] args) {
        System.out.println("Testes para a Arvore AVL");

        ArvoreAVL arvore = new ArvoreAVL(2);

        System.out.println("Inserindo o: 3");
        arvore.insercaoAVL(3);
        arvore.printArvore();

        System.out.println("Inserindo o: 1");
        arvore.insercaoAVL(1);
        arvore.printArvore();
    }
}
