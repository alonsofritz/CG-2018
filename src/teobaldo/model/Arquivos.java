package teobaldo.model;

import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Arquivos {
    private String string;
    
    public String openFIle() throws FileNotFoundException {
        JFileChooser fc = new JFileChooser();

        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            Scanner elem = new Scanner(fc.getSelectedFile());

            while (elem.hasNext()) {
                string = string + elem.next();
            }
        }
        return string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
