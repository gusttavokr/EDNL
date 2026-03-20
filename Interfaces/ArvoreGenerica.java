package Interfaces;

import java.util.Iterator;
import Classes.Node;

public interface ArvoreGenerica {

    // Métodos genéricos
    public int size();
    public int height();
    public boolean isEmpty();
    public Iterator<Object> elements();
    public Iterator<Node> nodes();  

    // Métodos de acesso
    public Node root();
    public Node parent(Node n);
    public Iterator<Node> children(Node n);

    // Métodos de consulta
    public boolean isExternal(Node n);
    public boolean isInternal(Node n);
    public boolean isRoot(Node n);
    public int depth(Node n);

    // Método de atualização
    public Object replace(Node n, Object o);
}
