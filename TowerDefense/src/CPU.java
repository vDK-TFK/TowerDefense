
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
public class CPU {
    
    private Castillo castilloCPU;
    private double numTropasCaminoSuperior = 0;
    private double numTropasCaminoInferior = 0;
    private double cantTropasTotales = 0;
    private Tropa tropaSeleccionada;
    private Cola listaTropasCPU = new Cola();
    private Cola listaTropasMostrar = new Cola();
    private Random random = new Random();
    
    public void tropaRandom(Camino Superior, Camino Inferior, Cola tropas){
        
        // Si la lista esta vacia, elije un personaje aleatorio
        if(listaTropasCPU.getLargo() == 0){
        int id = random.nextInt(3);
        tropaSeleccionada = tropas.encuentra(id);
        listaTropasCPU.encola(new Nodo(tropaSeleccionada));
       } 
        if ((numTropasCaminoSuperior / cantTropasTotales) > 0.75){
            Inferior.setTropa(tropaSeleccionada);
            Inferior.ingresarTropa(tropaSeleccionada);    
            numTropasCaminoInferior++;
            
            
        } else if((numTropasCaminoInferior / cantTropasTotales)>0.75){
            Superior.setTropa(tropaSeleccionada);
            Superior.ingresarTropa(tropaSeleccionada);
            numTropasCaminoSuperior++;
            
        } else{
            int numeroCamino = random.nextInt(2);
            if(numeroCamino == 1){
                Superior.setTropa(tropaSeleccionada);
                Superior.ingresarTropa(tropaSeleccionada);
                numTropasCaminoSuperior++;
                
            } else if(numeroCamino == 2){
                Inferior.setTropa(tropaSeleccionada);
                Inferior.ingresarTropa(tropaSeleccionada);
                numTropasCaminoInferior++;
            }
        }
        cantTropasTotales++;
    }
    
    private void mostrarUnidadesCPU(){
        for(int i=0; i<3; i++){
            listaTropasCPU.imprimir();
        }  
    }
    
}


