package teobaldo.controller;

import java.util.LinkedList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import teobaldo.model.Aresta;
import teobaldo.model.Face;
import teobaldo.model.Objeto;
import teobaldo.model.Vertice;

public class ObjetoController implements Cloneable {
    
    private LinkedList<Objeto> objetos;
    private Objeto obj;
    private Aresta a;
    
    boolean locked; // Se o objeto desenhado deve ser fechado ou não
    private Canvas canvasFrontal, canvasLateral, canvasTopo, canvasPerspectiva;
    
    public ObjetoController() {
        this.objetos = new LinkedList<>();
    }
    
    public ObjetoController(Canvas canvas1, Canvas canvas2, Canvas canvas3, Canvas canvas4) {
        if("canvasXY".equals(canvas1.getId())) {
            this.canvasFrontal = canvas1;
            this.canvasLateral = canvas2;
            this.canvasTopo = canvas3;
        }
        
        if("canvasYZ".equals(canvas1.getId())) {
            this.canvasFrontal = canvas2;
            this.canvasLateral = canvas1;
            this.canvasTopo = canvas3;
        }
        
        if("canvasXZ".equals(canvas1.getId())) {
            this.canvasFrontal = canvas3;
            this.canvasLateral = canvas2;
            this.canvasTopo = canvas1;
        }
        
        this.canvasPerspectiva = canvas4;
        this.objetos = new LinkedList<>();
    }
    
    public void setCanvas(Canvas canvas1, Canvas canvas2, Canvas canvas3, Canvas canvas4) {
        this.canvasFrontal = canvas1;
        this.canvasLateral = canvas2;
        this.canvasTopo = canvas3;
        this.canvasPerspectiva = canvas4;
    }
    
    public void draw() {
        System.out.println("Canvas: " + canvasFrontal.getId());
        
        GraphicsContext gcF = canvasFrontal.getGraphicsContext2D();
        GraphicsContext gcL = canvasLateral.getGraphicsContext2D();
        GraphicsContext gcT = canvasTopo.getGraphicsContext2D();
        
        //gcF.clearRect(-(canvasFrontal.getWidth())*2, -(canvasFrontal.getHeight())*2, canvasFrontal.getWidth()*2, canvasFrontal.getHeight()*2);
        //gcL.clearRect(-(canvasFrontal.getWidth())*2, -(canvasFrontal.getHeight())*2, canvasFrontal.getWidth()*2, canvasFrontal.getHeight()*2);
        //gcT.clearRect(-(canvasFrontal.getWidth())*2, -(canvasFrontal.getHeight())*2, canvasFrontal.getWidth()*2, canvasFrontal.getHeight()*2);
        
        if("canvasXY".equals(canvasFrontal.getId())) {
            objetos.forEach((objeto) -> {
                objeto.drawXY(gcF);
            });

            if (obj != null) {
                obj.drawXY(gcF);
            }

            objetos.forEach((objeto) -> {
                objeto.drawYZ(gcL);
            });

            if (obj != null) {
                obj.drawYZ(gcL);
            }

            objetos.forEach((objeto) -> {
                objeto.drawXZ(gcT);
            });

            if (obj != null) {
                obj.drawXZ(gcT);
            }
        }
        
        if("canvasYZ".equals(canvasFrontal.getId())) {
            objetos.forEach((objeto) -> {
                objeto.drawXY(gcT);
            });

            if (obj != null) {
                obj.drawXY(gcT);
            }

            objetos.forEach((objeto) -> {
                objeto.drawYZ(gcF);
            });

            if (obj != null) {
                obj.drawYZ(gcF);
            }

            objetos.forEach((objeto) -> {
                objeto.drawXZ(gcL);
            });

            if (obj != null) {
                obj.drawXZ(gcL);
            }
        }
        
        if("canvasXZ".equals(canvasFrontal.getId())) {
            objetos.forEach((objeto) -> {
                objeto.drawXY(gcT);
            });

            if (obj != null) {
                obj.drawXY(gcT);
            }

            objetos.forEach((objeto) -> {
                objeto.drawYZ(gcL);
            });

            if (obj != null) {
                obj.drawYZ(gcL);
            }

            objetos.forEach((objeto) -> {
                objeto.drawXZ(gcF);
            });

            if (obj != null) {
                obj.drawXZ(gcF);
            }
        }
    }
    
    public void setObj(Objeto obj) {
        this.obj = obj;
    }
    
    public LinkedList<Objeto> getObjetos() {
        return objetos;
    }
    
    public void setObjetos(LinkedList<Objeto> objetos) {
        this.objetos = objetos;
    }
    
    public void addPonto(Vertice p) {
        if (obj != null) {
            obj.addPonto(p);
            //obj.incNumLados();
            this.draw();
        }
    }
    
    public void addAresta(Aresta a) {
        if (obj != null) {
            obj.addAresta(a);
        }
    }
    
    public void addFace(Face f) {
        if(obj != null) {
            obj.addFace(f);
        }
    }
    
    public LinkedList<Vertice> getPontos() {
        return obj.getVertices();
    }
    
    public void startLine() {
        this.obj = new Objeto();
    }

    public void endLine() {
        if(this.locked == true) {
            obj.lockObject();
        }
        this.objetos.add(obj);
        //obj.calcCentro();
        this.obj = null;
        
        this.draw();
    }
    
    // SELEÇÕES
    public Objeto selecaoXY(Vertice p) {
        Objeto obj = null;

        double menor = 100000000, d1;
        int l = 0;
        
        for (Objeto objeto : objetos) {

            int t = objeto.getLados().size();

            for (int i = 1; i < t; i++) {
                d1 = distXY(objeto.getLados().get(i), objeto.getLados().get(i - 1), p);
                if (d1 < menor) {
                    if (d1 < 10) {
                        obj = objeto;
                        menor = d1;
                    }
                }
            }
            d1 = distXY(objeto.getLados().get(0), objeto.getLados().get(t - 1), p);
            if (d1 < menor) {
                if (d1 < 10) {
                    obj = objeto;
                    menor = d1;
                }
            }

        }
        return obj;
    }
    
    public Objeto selecaoYZ(Vertice p) {
        Objeto obj = null;

        double menor = 100000000, d1;
        int l = 0;
        for (Objeto objeto : objetos) {

            int t = objeto.getLados().size();

            for (int i = 1; i < t; i++) {
                d1 = distYZ(objeto.getLados().get(i), objeto.getLados().get(i - 1), p);
                if (d1 < menor) {
                    if (d1 < 10) {
                        obj = objeto;
                        menor = d1;
                    }
                }
            }
            d1 = distYZ(objeto.getLados().get(0), objeto.getLados().get(t - 1), p);
            if (d1 < menor) {
                if (d1 < 10) {
                    obj = objeto;
                    menor = d1;
                }
            }

        }
        return obj;
    }
    
    public Objeto selecaoXZ(Vertice p) {
        Objeto obj = null;

        double menor = 100000000, d1;
        int l = 0;
        for (Objeto objeto : objetos) {

            int t = objeto.getLados().size();

            for (int i = 1; i < t; i++) {
                d1 = distXZ(objeto.getLados().get(i), objeto.getLados().get(i - 1), p);
                if (d1 < menor) {
                    if (d1 < 10) {
                        obj = objeto;
                        menor = d1;
                    }
                }
            }
            d1 = distXZ(objeto.getLados().get(0), objeto.getLados().get(t - 1), p);
            if (d1 < menor) {
                if (d1 < 10) {
                    obj = objeto;
                    menor = d1;
                }
            }

        }
        return obj;
    }
    
    public double distXY(Vertice A, Vertice B, Vertice C) {
        double r, s, t, m, b, b2, m2, dist = 100, x, y;
        Vertice p;

        r = (B.getY() - A.getY());
        s = -(B.getX() - A.getX());
        t = B.getX() * A.getY() - B.getY() * A.getX();

        m = -r / s;
        b = -t / s;

        m2 = s / r;
        b2 = (C.getY() - ((s / r) * C.getX()));

        x = (b2 - b) / (m - m2);
        y = ((b2 * m) - (b * m2)) / (m - m2);

        p = new Vertice(x, y);

        if (Math.round(s) == 0) {
            p = new Vertice(B.getX(), C.getY());
        }

        if (Math.round(r) == 0) {
            p = new Vertice(C.getX(), B.getY());
        }

        if (equaçaoPontoXY(A, B, p)) {
            dist = distanciaRetaXY(p, C);
        }

        return dist;
    }
    
    public double distYZ(Vertice A, Vertice B, Vertice C) {
        double r, s, t, m, b, b2, m2, dist = 100, x, y, z = 0;
        Vertice p;

        r = (B.getY() - A.getY());
        s = -(B.getZ() - A.getZ());
        t = B.getZ() * A.getY() - B.getY() * A.getZ();

        m = -r / s;
        b = -t / s;

        m2 = s / r;
        b2 = (C.getY() - ((s / r) * C.getX()));

        x = (b2 - b) / (m - m2);
        y = ((b2 * m) - (b * m2)) / (m - m2);

        p = new Vertice(x, y);

        if (Math.round(s) == 0) {
            p = new Vertice(B.getZ(), C.getY());
        }

        if (Math.round(r) == 0) {
            p = new Vertice(C.getX(), B.getY());
        }

        if (equaçaoPontoYZ(A, B, p)) {
            dist = distanciaRetaYZ(p, C);
        }

        return dist;
    }
    
    public double distXZ(Vertice A, Vertice B, Vertice C) {
        double r, s, t, m, b, b2, m2, dist = 100, x, y, z = 0;
        Vertice p;

        r = (B.getZ() - A.getZ());
        s = -(B.getX() - A.getX());
        t = B.getX() * A.getZ() - B.getZ() * A.getX();

        m = -r / s;
        b = -t / s;

        m2 = s / r;
        b2 = (C.getY() - ((s / r) * C.getX()));

        x = (b2 - b) / (m - m2);
        y = ((b2 * m) - (b * m2)) / (m - m2);

        p = new Vertice(x, z);

        if (Math.round(s) == 0) {
            p = new Vertice(B.getX(), C.getY());
        }

        if (Math.round(r) == 0) {
            p = new Vertice(C.getX(), B.getZ());
        }

        if (equaçaoPontoXZ(A, B, p)) {
            dist = distanciaRetaXZ(p, C);
        }

        return dist;
    }
    
    public boolean equaçaoPontoXY(Vertice a, Vertice b, Vertice c) {
        double x, y, t, t2;

        if ((b.getY() == a.getY()) && (c.getY() == a.getY())) {
            if ((Math.max(b.getX(), a.getX()) > c.getX()) && (Math.min(b.getX(), a.getX()) < c.getX())) {
                return true;
            }
        }
        if ((b.getX() == a.getX()) && (c.getX() == a.getX())) {
            if ((Math.max(b.getY(), a.getY()) > c.getY()) && (Math.min(b.getY(), a.getY()) < c.getY())) {
                return true;
            }
        }
        t = (c.getX() - a.getX()) / (b.getX() - a.getX());
        t2 = (c.getY() - a.getY()) / (b.getY() - a.getY());

        return (t >= 0 && t <= 1) && (t2 >= 0 && t2 <= 1);
    }
    
    public boolean equaçaoPontoYZ(Vertice a, Vertice b, Vertice c) {
        double Z, y, t, t2;

        if ((b.getY() == a.getY()) && (c.getY() == a.getY())) {
            if ((Math.max(b.getZ(), a.getZ()) > c.getZ()) && (Math.min(b.getZ(), a.getZ()) < c.getZ())) {
                return true;
            }
        }
        
        if ((b.getZ() == a.getZ()) && (c.getZ() == a.getZ())) {
            if ((Math.max(b.getY(), a.getY()) > c.getY()) && (Math.min(b.getY(), a.getY()) < c.getY())) {
                return true;
            }
        }
        t = (c.getZ() - a.getZ()) / (b.getZ() - a.getZ());
        t2 = (c.getY() - a.getY()) / (b.getY() - a.getY());

        return (t >= 0 && t <= 1) && (t2 >= 0 && t2 <= 1);
    }

    public boolean equaçaoPontoXZ(Vertice a, Vertice b, Vertice c) {
        double x, Z, t, t2;

        if ((b.getZ() == a.getZ()) && (c.getY() == a.getZ())) {
            if ((Math.max(b.getX(), a.getX()) > c.getX()) && (Math.min(b.getX(), a.getX()) < c.getX())) {
                return true;
            }
        }
        if ((b.getX() == a.getX()) && (c.getX() == a.getX())) {
            if ((Math.max(b.getZ(), a.getZ()) > c.getY()) && (Math.min(b.getZ(), a.getZ()) < c.getY())) {
                return true;
            }
        }
        t = (c.getX() - a.getX()) / (b.getX() - a.getX());
        t2 = (c.getY() - a.getZ()) / (b.getZ() - a.getZ());

        return (t >= 0 && t <= 1) && (t2 >= 0 && t2 <= 1);
    }
    
    public double distanciaRetaXY(Vertice a, Vertice b) {
        double d;
        d = Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));

        return d;
    }
    
    public double distanciaRetaYZ(Vertice a, Vertice b) {
        double d;
        //d = Math.sqrt((a.getZ() - b.getX()) * (a.getZ() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
        d = Math.sqrt((a.getZ() - b.getZ()) * (a.getZ() - b.getZ()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
        return d;
    }
    
    public double distanciaRetaXZ(Vertice a, Vertice b) {
        double d;
        d = Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getZ() - b.getY()) * (a.getZ() - b.getY()));

        return d;
    }
}
