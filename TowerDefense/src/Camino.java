
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
public class Camino {
    
    private JLabel label;
    private Tropa tropa;
    
    public void ingresarTropa(){
        Icon icono = new ImageIcon(tropa.getCaracter().getImage().getScaledInstance
        (label.getWidth(),label.getHeight(),Image.SCALE_DEFAULT));
        label.setIcon(icono);
        label.repaint();  
    }
    
    public void eliminarTropa(){
        label.setIcon(null);
        label.repaint();
    }

    public JLabel getLabel() {
        return label;
    }

    public Tropa getTropa() {
        return tropa;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setTropa(Tropa tropa) {
        this.tropa = tropa;
    }    
}
