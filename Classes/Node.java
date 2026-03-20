package Classes;
import java.util.ArrayList;
import java.util.List;

public class Node{
    private Object element;
    private Node pai;
    private Node filhoE;
    private Node filhoD;

    private List<Node> filhos;

    public Node(Object o){
        this.element = o;
        this.filhos = new ArrayList<>();
    }

    // Métodos get
    public Object get_element(){
        return element;
    }

    public Node get_pai(){
        return pai;
    }

    public Node get_filhoE(){
        return filhoE;
    }

    public Node get_filhoD(){
        return filhoD;
    }

    public List<Node> get_filhos(){
        return filhos;
    }


    // Métodos set
    public void set_element(Object o){
        this.element = o;
    }

    public void set_pai(Node pai){
        this.pai = pai;
    }

    public void set_filhoE(Node filhoE){
        this.filhoE = filhoE;
    }
    public void set_filhoD(Node filhoD){
        this.filhoD = filhoD;
    }

    public void adiconar_filho(Node novo_filho){
        filhos.add(novo_filho);
    }
}