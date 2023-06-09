
public class Cola {

    private NodoCo frente;
    private NodoCo ultimo;
    private int largo;

    public Cola() {
        this.frente = null;
        this.ultimo = null;
        this.largo = 0;
    }

    public void encola(NodoCo nuevoNodo) {
        if (frente == null) {  // significa que la cola esta vacia
            frente = nuevoNodo;
            ultimo = nuevoNodo;
        } else {
            ultimo.setAtras(nuevoNodo);
            ultimo = nuevoNodo;
        }
        largo++;
    }

    public NodoCo tropaEliminar() {
        NodoCo aux = frente;
        if (frente != null) {
            frente = frente.getAtras();
            aux.setAtras(null);
            largo--;
        }
        return aux;
    }

    public Tropa encuentra(int id) {
        NodoCo aux = frente;
        while (id > 0) {
            aux = aux.getAtras();
        }
        return aux.getTropa();
    }

    public Tropa ultimaPosicion() {
        NodoCo aux = frente;
        while (aux == null) {
            if (aux.getAtras() != null) {
                aux = aux.getAtras();
            }
        }

        return aux.getTropa();
    }

    public String imprimir() {
        String s = "";
        NodoCo aux = frente;
        while (aux != null) {
            s += aux.getTropa().getTipo().getNombre() + "\n";
            aux = aux.getAtras();
        }
        return s;
    }

    public int getLargo() {
        return largo;
    }

}
