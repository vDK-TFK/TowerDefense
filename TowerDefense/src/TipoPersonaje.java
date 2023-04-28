public class TipoPersonaje {
    
    private int idTipo;
    private String nombre;
    private int idDebilidad;

    public TipoPersonaje(int idTipo, String nombre, int idDebilidad) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.idDebilidad = idDebilidad;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdDebilidad() {
        return idDebilidad;
    }

    public void setIdDebilidad(int idDebilidad) {
        this.idDebilidad = idDebilidad;
    }
}
