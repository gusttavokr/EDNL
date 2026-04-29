package Classes;

public class NodeB {
 
    private Object[] chaves; // Array de chaves
    private int t; // Grau ou Ordem
    private NodeB[] filhos;
    private int numChaves;

    public NodeB(int t){
        this.t = t;
        this.chaves = new Object[2 * t -1];
        this.filhos = new NodeB[2 * t];
        this.numChaves = 0;
    }

    public Object[] get_chaves(){
        return this.chaves;
    }

    public int get_grau(){
        return this.t;
    }

    public NodeB[] get_filhos(){
        return this.filhos;
    }

    public int get_numChaves(){
        return this.numChaves;
    }
}