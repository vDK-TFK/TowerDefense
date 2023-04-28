
public class NodoCo {
        private Tropa tropa;
    private NodoCo atras;

    public NodoCo(Tropa tropa) {
        this.tropa = tropa;
        this.atras = null;
    }

    public Tropa getTropa() {
        return tropa;
    }

    public void setTropa(Tropa tropa) {
        this.tropa = tropa;
    }

    public NodoCo getAtras() {
        return atras;
    }

    public void setAtras(NodoCo atras) {
        this.atras = atras;
    }
    
}
