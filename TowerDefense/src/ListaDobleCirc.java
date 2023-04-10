
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
public class ListaDobleCirc {
    
    private Camino p;
    private NodoCirc cabeza;
    private NodoCirc ultimo;
    private int largo = 0;

    public void inserta(Camino p) {
        if (cabeza == null) {
            cabeza = new NodoCirc(p);
            ultimo = cabeza;
            ultimo.setNext(cabeza);
            ultimo.setBack(cabeza);
        } else {
            NodoCirc aux = new NodoCirc(p);
            aux.setNext(cabeza);
            aux.setBack(ultimo);
            ultimo.setNext(aux);
            cabeza.setBack(aux);
        }
    }

    public Camino extrae(int i) {
        NodoCirc aux = null;
        if (cabeza == null) {
            System.out.println("La lista esta vacia");
        } else {
            aux = cabeza;
            while (i > 0) {
                aux = aux.getNext();
                i--;
            }
        }
        return aux.getDato();
    }

    public int getLargo() {
        return largo;
    }
    
    public boolean isCabeza(int i) {
        if (cabeza == null) {
            return false;
        } else {
            NodoCirc aux = cabeza;
            while (i > 0) {
                aux = aux.getNext();
                i--;
            }
            if (aux == cabeza) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public boolean isUltimo(int i) {
        if (cabeza == null) {
            return false;
        } else {
            NodoCirc aux = cabeza;
            while (i > 0) {
                aux = aux.getNext();
                i--;
            }
            if (aux == ultimo) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    
}
