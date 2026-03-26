package Tests;

import Classes.ArvoreGenerica;
import Classes.Node;

public class TesteArvoreGenerica {
    public static void main(String[] args) {
        System.out.println("Testes para a Arvore Binaria");

        ArvoreGenerica arvore = new ArvoreGenerica(5);
        Node raiz = arvore.root();
        Node filho1 = new Node(3);
        raiz.adicionar_filho(filho1);
        filho1.set_pai(raiz);


        System.out.println("Tamanho da árvore: " + arvore.size());
        System.out.println("Profundidade do nó raiz: " + arvore.depth(filho1));
        arvore.elements().forEachRemaining(System.out::println);
        arvore.nodes().forEachRemaining(System.out::println);
    }
}