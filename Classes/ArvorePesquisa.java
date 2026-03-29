package Classes;

public class ArvorePesquisa extends ArvoreBinaria {
    public ArvorePesquisa(Object o){
        super(o);
    }

    public Node busca(Object o, Node n){
        if (isExternal(n)) {
            return n;
        }

        if (comparar(o, n.get_element()) < 0) {
            return busca(o, leftChild(n));
        }

        else if (comparar(o, n.get_element()) == 0){
            return n;
        } else{
            if (hasRight(n)){
                return busca(o, rightChild(n));
            }
        }

        return n;
    }

    private int comparar(Object o, Object p) {
        int pInt = converterInt(p);
        int oInt = converterInt(o);

        return Integer.compare(pInt, oInt);
    }

    private int converterInt(Object p) {

        if (p instanceof Integer) {
            return (Integer) p;
        }
        if (p instanceof Float) {
            return Math.round((Float) p);
        }
        if (p instanceof String) {
            return Integer.parseInt((String) p);
        }
        if (p instanceof Boolean) {
            return ((Boolean) p) ? 1 : 0;
        }
        
        return 0;
    }
}
