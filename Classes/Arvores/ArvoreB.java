package Classes.Arvores;

import Classes.NodeB;

public class ArvoreB {
    private NodeB raiz;
    private int t;

    public ArvoreB(int t){
        this.t = t;
        this.raiz = new NodeB(t);
    }
}
