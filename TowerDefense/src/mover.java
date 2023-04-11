/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author VDK
 */
public class mover extends Thread {

    private ListaDobleCirc caminosuperior;
    private ListaDobleCirc caminoinferior;
    private Castillo cpuCastillo;
    private PantallaJuego pantalla;
    private Enfrentamiento batalla = new Enfrentamiento();
    //private MoverCpu moverCpu;

    @Override
    public void run() {
        if (cpuCastillo.castilloDestruido() != false) {
            start();
        }
    }

    public void mover(ListaDobleCirc camino) {
        for (int i = 3; i >= 0; i--) {
            if (camino.extrae(i).getTropa().getCaracter() != null) {

                if (camino.isCabeza(i)) {
                    cpuCastillo.danoCastillo(camino.extrae(i).getTropa().getDanoCastillo());
                    camino.extrae(i).setTropa(null);
                    camino.extrae(i).eliminarTropa();

                    if (cpuCastillo.castilloDestruido()) {
                        System.out.println("Ganador: Jugador");
                    }
                } else if (camino.extrae(i + 1).getTropa() != null) {
                    batalla.Enfrentamiento(camino.extrae(i + 1), camino.extrae(i));

                    if (camino.extrae(+1).getTropa() == null) {
                        //poner la logica del logicaCpu
                    } else {
                        //poner la logica del cpu
                    }

                    if (camino.extrae(i).getTropa() != null) {
                        camino.extrae(i + 1).setTropa(camino.extrae(i).getTropa());
                        camino.extrae(i + 1).ingresarTropa();
                        camino.extrae(i).setTropa(null);
                        camino.extrae(i).eliminarTropa();
                    } else {
                        // parte de la pantalla
                    }
                } else {
                    camino.extrae(i + 1).setTropa(camino.extrae(i).getTropa());
                    camino.extrae(i + 1).ingresarTropa();
                    camino.extrae(i).setTropa(null);
                    camino.extrae(i).eliminarTropa();
                }
            }
        }
    }

    public mover(ListaDobleCirc caminosuperior, ListaDobleCirc caminoinferior, Castillo cpuCastillo, PantallaJuego pantalla) {
        this.caminosuperior = caminosuperior;
        this.caminoinferior = caminoinferior;
        this.cpuCastillo = cpuCastillo;
        this.pantalla = pantalla;
    }

}
