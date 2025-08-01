package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class EventoCultural implements Serializable {
    private String nombre;
    private LocalDate fecha;
    private EspacioCultural espacio;
    private boolean requiereReserva;
    private int capacidadActual;

    public EventoCultural(String nombre, LocalDate fecha, EspacioCultural espacio, boolean requiereReserva) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.espacio = espacio;
        this.requiereReserva = requiereReserva;
        this.capacidadActual = 0;
    }

    // Constructor alternativo con String para compatibilidad
    public EventoCultural(String nombre, String fechaStr, EspacioCultural espacio, boolean requiereReserva) {
        this.nombre = nombre;
        this.fecha = LocalDate.parse(fechaStr);
        this.espacio = espacio;
        this.requiereReserva = requiereReserva;
        this.capacidadActual = 0;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EspacioCultural getEspacio() {
        return espacio;
    }

    public void setEspacio(EspacioCultural espacio) {
        this.espacio = espacio;
    }

    public boolean isRequiereReserva() {
        return requiereReserva;
    }

    public void setRequiereReserva(boolean requiereReserva) {
        this.requiereReserva = requiereReserva;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public boolean puedeAgregarVisitante() {
        return capacidadActual < espacio.getCapacidadMaxima();
    }

    public void agregarVisitante() {
        if (puedeAgregarVisitante()) {
            capacidadActual++;
        }
    }

    @Override
    public String toString() {
        return "EventoCultural{" +
                "nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", espacio=" + espacio.getNombre() +
                ", requiereReserva=" + requiereReserva +
                ", ocupacion=" + capacidadActual + "/" + espacio.getCapacidadMaxima() +
                '}';
    }
}
