package Tests;

import Classes.ArvorePesquisa;

public class TesteArvorePesquisa {
    public static void main(String[] args) {
        System.out.println("Testes para a Arvore Binaria");

        ArvorePesquisa arvore = new ArvorePesquisa(6);
        
        arvore.insert(2);
        arvore.insert(4);
        arvore.insert(9);
        arvore.insert(8);
        arvore.insert(1);
        
        arvore.printArvore();

        System.out.println("Inserindo elemento repetido: " + 1);
        arvore.insert(1);
    }
}
