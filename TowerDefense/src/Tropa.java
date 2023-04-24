
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
public class Tropa {
    
    private String tipoTropa;
    private String fortaleza;
    private String debilidad;
    private double danoCastillo;
    private ImageIcon caracter;
    private boolean tropaJugador = false;
    
    
    /*
    * El constructor de esta clase se realiza en base a al tipo de personaje,
    ya que este determinar cuanto daño puede realizar el personaje.
    */ 

    public Tropa(String tipoTropa) {
        this.tipoTropa = tipoTropa;
        
        if(tipoTropa.equals("Mago")){
            this.fortaleza = "Caballero";
            this.debilidad = "Arquero";
            this.danoCastillo = 1.5;
            this.caracter = new ImageIcon("src/Imagenes/mago.jpeg");
        } else if(tipoTropa.equals("Caballero")){
            this.fortaleza = "Arquero";
            this.debilidad = "Mago";
            this.danoCastillo = 2;
            this.caracter = new ImageIcon("src/Imagenes/caballero.jpeg");
        } else if(tipoTropa.equals("Arquero")){
            this.fortaleza = "Mago";
            this.debilidad = "Caballero";
            this.danoCastillo = 1;
            this.caracter = new ImageIcon("src/Imagenes/arquero.jpeg");
        }
    }
    
     // Metodos get

    public String getTipoTropa() {
        return tipoTropa;
    }

    public String getFortaleza() {
        return fortaleza;
    }

    public String getDebilidad() {
        return debilidad;
    }

    public double getDanoCastillo() {
        return danoCastillo;
    }

    public ImageIcon getCaracter() {
        return caracter;
    }

    public boolean isTropaJugador() {
        return tropaJugador;
    }
    
    //Metodos set

    public void setFortaleza(String fortaleza) {
        this.fortaleza = fortaleza;
    }

    public void setDebilidad(String debilidad) {
        this.debilidad = debilidad;
    }

    public void setDanoCastillo(double danoCastillo) {
        this.danoCastillo = danoCastillo;
    }

    public void setCaracter(ImageIcon caracter) {
        this.caracter = caracter;
    }

    public void setTropaJugador(boolean tropaJugador) {
        this.tropaJugador = tropaJugador;
    }
    
     public void setTipoTropa(String tipoTropa) {
        this.tipoTropa = tipoTropa;
        
        if(tipoTropa.equals("Mago")){
            this.fortaleza = "Caballero";
            this.debilidad = "Arquero";
            this.danoCastillo = 1.5;
            this.caracter = new ImageIcon("src/Imagenes/arquero.jpeg");
        } else if(tipoTropa.equals("Caballero")){
            this.fortaleza = "Arquero";
            this.debilidad = "Mago";
            this.danoCastillo = 2;
            this.caracter = new ImageIcon("src/Imagenes/caballero.caballero.jpeg");
        } else if(tipoTropa.equals("Arquero")){
            this.fortaleza = "Mago";
            this.debilidad = "Caballero";
            this.danoCastillo = 1;
            this.caracter = new ImageIcon("src/Imagenes/arquero.jpeg");
        }
        
    }
     
   public void eliminarTropa(Camino camino){
       camino.eliminarTropa();
       camino.setTropa(null);
   }
     
        
        
        
        
    

    
    
    
    
    
}
