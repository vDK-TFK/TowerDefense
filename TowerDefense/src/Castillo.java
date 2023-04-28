/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
public class Castillo {
    
    private double vida = 10;

    // Constructor vacio
    public Castillo(double vida) {
        this.vida = vida;
    }

    // Get y sets de la vida del castillo
    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    //Metodo para bajar la vida del castillo
    public void danoCastillo(double dano) {
        this.vida -= dano;
    }

    // Metodo para saber si el castillo ha sido destruido
    public boolean castilloDestruido() {
        if (vida <= 0) {
            return true;
        }
        return false;
    }
}
