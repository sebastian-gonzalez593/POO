package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Reserva implements Serializable {
    private LocalDate fecha;
    private EspacioCultural espacio;
    private Visitante visitante;

    public Reserva(LocalDate fecha, EspacioCultural espacio, Visitante visitante) {
        this.fecha = fecha;
        this.espacio = espacio;
        this.visitante = visitante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public EspacioCultural getEspacio() {
        return espacio;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "fecha=" + fecha +
                ", espacio=" + espacio.getNombre() +
                ", visitante=" + visitante.getNombre() +
                '}';
    }
}
