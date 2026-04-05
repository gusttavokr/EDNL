package Classes;

public class NodeAVL extends Node {
    private int FB;

    public NodeAVL(Object o){
        super(o);
    }

    public int get_FB(){
        return FB;
    }

    public void set_FB(int v){
        this.FB = v;
    }
}
