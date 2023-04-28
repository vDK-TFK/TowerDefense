
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Tropa {

    private TipoPersonaje tipo;
    private double dano;
    private ImageIcon imagenTropa;
    private boolean tropaJugador = false;
    private int poscionActual;

    public Tropa(TipoPersonaje tipo, ImageIcon imagen, double dano) {
        this.tipo = tipo;
        this.dano = dano;
        this.imagenTropa = imagen;
    }

    public TipoPersonaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoPersonaje tipo) {
        this.tipo = tipo;
    }

    public double getDano() {
        return dano;
    }

    public void setDano(double dano) {
        this.dano = dano;
    }

    public ImageIcon getCaracter() {
        return imagenTropa;
    }

    public void setCaracter(ImageIcon caracter) {
        this.imagenTropa = caracter;
    }

    public boolean isTropaJugador() {
        return tropaJugador;
    }

    public void setTropaJugador(boolean tropaJugador) {
        this.tropaJugador = tropaJugador;
    }

    public int getPoscionActual() {
        return poscionActual;
    }

    public void setPoscionActual(int poscionActual) {
        this.poscionActual = poscionActual;
    }
}
        
