
public class NodoCirc {

    private NodoCirc back;
    private NodoCirc next;

    public NodoCirc(NodoCirc back, NodoCirc next) {
        this.back = back;
        this.next = next;
    }

    public NodoCirc getBack() {
        return back;
    }

    public void setBack(NodoCirc back) {
        this.back = back;
    }

    public NodoCirc getNext() {
        return next;
    }

    public void setNext(NodoCirc next) {
        this.next = next;
    }
}
