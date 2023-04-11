/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
public class NodoCirc {
    
    private Camino dato;
    private NodoCirc back;
    private NodoCirc next;

    public NodoCirc(Camino dato) {
        this.dato = dato;
    }

    public Camino getDato() {
        return dato;
    }

    public NodoCirc getBack() {
        return back;
    }

    public NodoCirc getNext() {
        return next;
    }

    public void setDato(Camino dato) {
        this.dato = dato;
    }

    public void setBack(NodoCirc back) {
        this.back = back;
    }

    public void setNext(NodoCirc next) {
        this.next = next;
    }
    
    
    

}
