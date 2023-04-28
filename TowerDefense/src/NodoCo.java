
public class NodoCo {
    private Tropa dato;
    private NodoCo atras;
    
    public NodoCo(Tropa dato) {
        this.dato = dato;
        this.atras = null;
    }

    public Tropa getDato() {
        return dato;
    }

    public void setDato(Tropa dato) {
        this.dato = dato;
    }

    public NodoCo getAtras() {
        return atras;
    }

    public void setAtras(NodoCo atras) {
        this.atras = atras;
    }
    
}