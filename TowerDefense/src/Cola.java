/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
public class Cola {
    
    private NodoCo frente;
    private NodoCo ultimo;
    private int largo;

    public Cola() {
        this.frente = null;
        this.ultimo = null;
        this.largo = 0;
    }
        
    public void encola(NodoCo nuevoNodo){
        if(frente == null){  // significa que la cola esta vacia
            frente = nuevoNodo;
            ultimo = nuevoNodo;                    
        } else{
            ultimo.setAtras(nuevoNodo);
            ultimo=nuevoNodo;
        }
        largo++;
    }
    
    public NodoCo atiende(){
        NodoCo aux = frente;
        if(frente!=null){
            frente=frente.getAtras();
            aux.setAtras(null);
            largo--;
        }
        return aux;
    }
    
    public Tropa encuentra(int id){
        NodoCo aux = frente;
        while(id > 0){
            aux = aux.getAtras();
        }
        return aux.getDato();
    }
    
    public String imprimir(){
        String s="";
        NodoCo aux=frente;
        while(aux!=null){
            s+=aux.getDato() +"\n";
            aux=aux.getAtras();
        }
        return s;
    }

    public int getLargo() {
        return largo;
    }
    
    
    
    
}
