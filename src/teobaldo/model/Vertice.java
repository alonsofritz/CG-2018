package teobaldo.model;

import java.io.Serializable;

public class Vertice implements Serializable {
    
    private double x;
    private double y;
    private double z;

    public Vertice(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vertice(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    
    public Vertice(double z) {
        this.z = z;
    }
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
    
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
    
    @Override
    public String toString() {
        return "x = " + x + ", y = " + y + ", z = " + z;
    }
}