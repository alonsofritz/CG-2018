package teobaldo.model;

import java.util.LinkedList;

public class Face {
    
    private LinkedList<Aresta> arestas;
    
    public Face() {
        this.arestas = new LinkedList<>();
    }
    
    public Face(LinkedList<Aresta> arestas) {
        this.arestas = arestas;
    }
    
    public void addAresta(Aresta aresta) {
        this.arestas.add(aresta);
    }
    
    public LinkedList<Aresta> getArestas() {
        return this.arestas;
    }
    
    @Override
    public String toString() {
        return "Face \n Aresta: " + this.getArestas();
    }
}
