/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
public class Enfrentamiento {

    public void Enfrentamiento(Camino caminoJugador, Camino caminoCPU){
        if(caminoJugador.getTropa().getFortaleza() == caminoCPU.getTropa().getDebilidad()){
            caminoCPU.eliminarTropa();
        } else if(caminoJugador.getTropa().getDebilidad() == caminoCPU.getTropa().getFortaleza()){
            caminoJugador.eliminarTropa();
        } else{
            caminoJugador.eliminarTropa();
            caminoCPU.eliminarTropa();
        }
    }    
}
