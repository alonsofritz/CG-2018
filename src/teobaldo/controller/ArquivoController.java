package teobaldo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;
import javafx.scene.paint.Color;
import javax.swing.JFileChooser;
import teobaldo.model.Objeto;
import teobaldo.model.Vertice;

public class ArquivoController {
    
    public ArquivoController() {
        
    }
    
    public void salvar(LinkedList<Objeto> objeto) throws IOException {

        File f = null;
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if (file.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            f = new File(file.getSelectedFile().getAbsolutePath() + ".txt");

            FileWriter fw = new FileWriter(f);

            int i = 1;
            String ponto;
            String obj;
            
            for (Objeto poli : objeto) {
                obj = "P" + String.valueOf(i) + "\r\n";

                fw.write(obj);
                i++;
                for (Vertice arestas : poli.getLados()) {
                    ponto = "x: " + arestas.getX() + " " + "y: " + arestas.getY() + " \r\n";
                    fw.write(ponto);
                }
            }

            fw.flush();
            fw.close();
        }
    }
    
    public void save(LinkedList<Objeto> objeto) {
        File f = null;
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (file.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

            f = new File(file.getSelectedFile().getAbsolutePath() + ".txt");
            FileOutputStream out;
            try {
                out = new FileOutputStream(file.getSelectedFile().getAbsolutePath() + ".yes");
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(objeto);
            } catch (IOException e) {
            }
        }
    }

    public void load(ObjetoController objCtrl) {
        String ret = null;
        JFileChooser fc = new JFileChooser();

        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream in = new FileInputStream(fc.getSelectedFile().getAbsolutePath());
                ObjectInputStream ois = new ObjectInputStream(in);
                objCtrl.setObjetos((LinkedList<Objeto>) (ois.readObject()));
            } catch (Exception e) {
                System.out.println("Problem serializing: " + e);
            }
        }
    }
}
