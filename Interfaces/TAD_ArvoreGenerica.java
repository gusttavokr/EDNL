package Interfaces;

import java.util.Iterator;
import Classes.Node;

public interface TAD_ArvoreGenerica {

    // Métodos genéricos
    public int size(); //feito
    public int height(Node n); //feito
    public boolean isEmpty(); //feito
    public Iterator<Object> elements(); //feito
    public Iterator<Node> nodes();  //feito

    // Métodos de acesso
    public Node root(); //feito
    public Node parent(Node n); //feito
    public Iterator<Node> children(Node n); //feito

    // Métodos de consulta
    public boolean isExternal(Node n); //feito
    public boolean isInternal(Node n); //feito
    public boolean isRoot(Node n); //feito
    public int depth(Node n); //feito

    // Método de atualização
    public Object replace(Node n, Object o);
}
