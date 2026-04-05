package Tests;

import Classes.ArvorePesquisa;
import Classes.Node;

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
        
        // // System.out.println("Inserindo elemento repetido: " + 1);
        // // arvore.insert(1);
        
        Node raiz = arvore.root();
        Node removido = arvore.busca("1", raiz);
        System.out.println("Node a ser removido: " + removido.get_element());
                
        arvore.remove(removido);
        arvore.printArvore();

        Node removido2 = arvore.busca("2", raiz);
        System.out.println("Node a ser removido: " + removido2.get_element());

        // Node pai_removido2 = arvore.parent(removido2);
        // Node filho_removido2 = arvore.rightChild(removido2);
        // System.out.println("O pai de " + removido2.get_element() + " é: " + pai_removido2.get_element());
        // System.out.println("O filho direito de " + removido2.get_element() + " é: " + filho_removido2.get_element());

        arvore.remove(removido2);
        arvore.printArvore();
        // Node seis = arvore.busca("6", raiz);
        // arvore.remove(seis);
        
        Node removido3 = arvore.busca("6", raiz);
        System.out.println("Node a ser removido: " + removido3.get_element());
        arvore.remove(removido3);

        arvore.printArvore();
    }
}
