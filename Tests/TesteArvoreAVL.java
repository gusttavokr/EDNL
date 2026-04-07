package Tests;

import Classes.ArvoreAVL;

public class TesteArvoreAVL {
    public static void main(String[] args) {
        System.out.println("Testes para a Arvore AVL");

//        ArvoreAVL arvore = new ArvoreAVL(3);
//
//
//        System.out.println("Inserindo o: 2");
//        arvore.insercaoAVL(2);
//        arvore.printArvore();
//
//        System.out.println("Inserindo o: 1");
//        arvore.insercaoAVL(1);
//        arvore.printArvore();
//
//        System.out.println("Inserindo o: 5");
//        arvore.insercaoAVL(5);
//        arvore.printArvore();
//
//        System.out.println("Inserindo o: 6");
//        arvore.insercaoAVL(6);
//        arvore.printArvore();


        // Desbalanceada simples pra esquerda
//         ArvoreAVL arvore = new ArvoreAVL(1);
//
//         System.out.println("Inserindo o: 2");
//         arvore.insercaoAVL(2);
//         arvore.printArvore();
//         System.out.println("Inserindo o: 3");
//         arvore.insercaoAVL(3);
//         arvore.printArvore();

        // Desbalanceada simples pra direita
         ArvoreAVL arvore = new ArvoreAVL(3);

         System.out.println("Inserindo o: 2");
         arvore.insercaoAVL(2);
         arvore.printArvore();
         System.out.println("Inserindo o: 1");
         arvore.insercaoAVL(1);
         arvore.printArvore();

//        ArvoreAVL arvore = new ArvoreAVL(10);
//
//        System.out.println("Inserindo o: 20");
//        arvore.insercaoAVL(20);
//        arvore.printArvore();
//        System.out.println("Inserindo o: 30");
//        arvore.insercaoAVL(30);
//        arvore.printArvore();
//        System.out.println("Inserindo o: 15");
//        arvore.insercaoAVL(15);
//        arvore.printArvore();
    }
}
