package Classes;
import java.util.ArrayList;
import java.util.List;

public class Node{
    private Object element;
    private Node pai;

    // Árvore Genérica
    private List<Node> filhos;

    // Árvore Binária + Pesquisa
    private Node filhoE;
    private Node filhoD;

    // Árvore AVL
    private int FB;

    // Árvore Rubro-Negra
    private Cor cor;

    public Node(Object o){
        this.element = o;
        this.filhos = new ArrayList<>();
        this.FB = 0;
        this.cor = Cor.NEGRO;
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

    public int get_FB(){ return FB; }

    public Cor get_Cor(){
        return this.cor;
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

    public void adicionar_filho(Node novo_filho){
        filhos.add(novo_filho);
    }

    public void remover_filho(Node filho){
        filhos.remove(filho);
    }

    public void set_FB(int i){ this.FB = i; }

    public void set_Cor(Cor cor){
        this.cor = cor;
    }
}