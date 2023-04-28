
public class Jugador {

    private Castillo casJugador;
    private Cola tropaSup = new Cola();
    private Cola tropaInf = new Cola();

    public Jugador(Castillo casJugador) {
        this.casJugador = casJugador;
    }

    public Castillo getCasJugador() {
        return casJugador;
    }

    public void setCasJugador(Castillo casJugador) {
        this.casJugador = casJugador;
    }

    public Cola getTropasSup() {
        return tropaSup;
    }

    public Cola getTropaInf() {
        return tropaInf;
    }
}
  
