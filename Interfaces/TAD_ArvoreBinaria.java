package Interfaces;

import Classes.Node;

public interface TAD_ArvoreBinaria extends TAD_ArvoreGenerica {
    public Node leftChild(Node n);
    public Node rightChild(Node n);
    public boolean hasLeft(Node n);
    public boolean hasRight(Node n);
} 
