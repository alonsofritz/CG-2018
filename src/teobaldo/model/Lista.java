package teobaldo.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Lista implements Serializable {
    private LinkedList<Double> lista;
    private int y;

    public Lista(LinkedList<Double> lista, int y) {
        this.lista = lista;
        this.y = y;
        
    }
    
    public Lista(int y) {
        this.y = y;
        this.lista = new LinkedList<>();
    }
    
    public Lista() {
        lista = new LinkedList<>();
    }

    public LinkedList<Double> getLista() {
        return lista;
    }

    public void add(double d){
        this.lista.add(d);
    }
    
    public void setLista(LinkedList<Double> lista) {
        this.lista = lista;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
