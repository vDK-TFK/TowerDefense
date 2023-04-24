/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author VDK
 */
public class MoverCpu extends Thread {
    private ListaDobleCirc caminosuperior;
    private ListaDobleCirc caminoinferior;
    private Castillo jugadorCastillo;
    private PantallaJuego pantalla;
    private Enfrentamiento batalla = new Enfrentamiento();
    private CPU CPU;
    
    public MoverCpu(ListaDobleCirc caminosuperior, ListaDobleCirc caminoinferior, Castillo jugadorCastillo, PantallaJuego pantalla, CPU CPU) {
        this.caminosuperior = caminosuperior;
        this.caminoinferior = caminoinferior;
        this.jugadorCastillo = jugadorCastillo;
        this.pantalla = pantalla;
        this.CPU = CPU;
    }

    //private MoverCpu moverCpu;
    
    @Override
    public void run() {
        if (jugadorCastillo.castilloDestruido() != false) {
            start();
        }
    }

    public void moverCPU (ListaDobleCirc camino) {
        for (int i = 0; i < camino.getLargo(); i++) {
            if (camino.extrae(i).getTropa().getCaracter() != null) {
                
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                }
                
                if (camino.isUltimo(i)) {
                    jugadorCastillo.danoCastillo(camino.extrae(i).getTropa().getDanoCastillo());
                    camino.extrae(i).setTropa(null);
                    camino.extrae(i).eliminarTropa();

                    if (jugadorCastillo.castilloDestruido()) {
                        System.out.println("Ganador: CPU");
                    }
                } else if (camino.extrae(i - 1).getTropa() != null) {
                    batalla.Enfrentamiento(camino.extrae(i - 1), camino.extrae(i));

                    if (camino.extrae(i-1).getTropa() == null) {
                        //poner la logica del logicaCpu
                    } else {
                        //poner la logica del cpu
                    }

                    if (camino.extrae(i).getTropa() != null) {
                        camino.extrae(i - 1).setTropa(camino.extrae(i).getTropa());
                        camino.extrae(i - 1).ingresarTropa();
                        camino.extrae(i).setTropa(null);
                        camino.extrae(i).eliminarTropa();
                    } else {
                        // parte de la pantalla
                    }
                } else {
                    camino.extrae(i - 1).setTropa(camino.extrae(i).getTropa());
                    camino.extrae(i - 1).ingresarTropa();
                    camino.extrae(i).setTropa(null);
                    camino.extrae(i).eliminarTropa();
                }
            }
        }
    }

    
    
    
}
