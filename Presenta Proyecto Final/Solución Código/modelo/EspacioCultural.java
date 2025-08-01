package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EspacioCultural implements Serializable {
    private String nombre;
    private int capacidadMaxima;
    private String ubicacion;
    private String horarios;
    private List<String> tiposEventos;

    public EspacioCultural(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.ubicacion = "";
        this.horarios = "9:00-18:00";
        this.tiposEventos = new ArrayList<>();
    }

    public EspacioCultural(String nombre, int capacidadMaxima, String ubicacion, String horarios) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.ubicacion = ubicacion;
        this.horarios = horarios;
        this.tiposEventos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public List<String> getTiposEventos() {
        return tiposEventos;
    }

    public void agregarTipoEvento(String tipoEvento) {
        if (!tiposEventos.contains(tipoEvento)) {
            tiposEventos.add(tipoEvento);
        }
    }

    @Override
    public String toString() {
        return "EspacioCultural{" +
                "nombre='" + nombre + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                ", ubicacion='" + ubicacion + '\'' +
                ", horarios='" + horarios + '\'' +
                ", tiposEventos=" + tiposEventos +
                '}';
    }
}
