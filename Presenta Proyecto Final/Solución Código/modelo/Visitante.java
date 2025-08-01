package modelo;

import java.io.Serializable;

public abstract class Visitante implements Serializable {
    private String nombre;

    public Visitante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract String getTipo();
}

