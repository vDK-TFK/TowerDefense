
public class CPU {

    private Castillo casCPU;
    private Cola tropaSup = new Cola();
    private Cola tropaInf = new Cola();

    public CPU() {
    }

    public CPU(Castillo casCPU) {
        this.casCPU = casCPU;
    }

    public Castillo getCasCPU() {
        return casCPU;
    }

    public void setCasCPU(Castillo casCPU) {
        this.casCPU = casCPU;
    }

    public Cola getTropaSup() {
        return tropaSup;
    }

    public void setTropaSup(Cola tropaSup) {
        this.tropaSup = tropaSup;
    }

    public Cola getTropaInf() {
        return tropaInf;
    }

    public void setTropaInf(Cola tropaInf) {
        this.tropaInf = tropaInf;
    }
}
