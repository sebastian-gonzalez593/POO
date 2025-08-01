package controlador;

import modelo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorReservas {
    private ArchivoSerializador archivos;
    private List<Visitante> visitantes;
    private List<EspacioCultural> espacios;
    private List<Reserva> reservas;
    private List<EventoCultural> eventos;

    public ControladorReservas() {
        archivos = new ArchivoSerializador();
        cargarTodosLosDatos();
    }

    private void cargarTodosLosDatos() {
        visitantes = new ArrayList<>(archivos.cargarVisitantes());
        espacios = new ArrayList<>(archivos.cargarEspacios());
        reservas = new ArrayList<>(archivos.cargarReservas());
        eventos = new ArrayList<>(archivos.cargarEventos());
    }

    public void agregarVisitante(Visitante visitante) {
        visitantes.add(visitante);
        archivos.guardarVisitantes(new ArrayList<>(visitantes));
    }

    public void agregarEspacio(EspacioCultural espacio) {
        espacios.add(espacio);
        archivos.guardarEspacios(new ArrayList<>(espacios));
    }

    public void agregarEvento(EventoCultural evento) {
        eventos.add(evento);
        archivos.guardarEventos(new ArrayList<>(eventos));
    }

    public void registrarReserva(String fechaStr, EspacioCultural espacio, Visitante visitante) {
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr);
        } catch (Exception e) {
            System.err.println("Fecha inválida: " + e.getMessage());
            return;
        }

        // Verificar capacidad del espacio
        int reservasEnFecha = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getFecha().equals(fecha) && reserva.getEspacio().equals(espacio)) {
                reservasEnFecha++;
            }
        }

        if (reservasEnFecha >= espacio.getCapacidadMaxima()) {
            System.out.println("️ Espacio lleno para la fecha " + fecha);
            return;
        }

        Reserva reserva = new Reserva(fecha, espacio, visitante);
        reservas.add(reserva);
        archivos.guardarReservas(new ArrayList<>(reservas));
        System.out.println(" Reserva creada exitosamente");
    }

    public Visitante buscarVisitante(String nombre) {
        for (Visitante v : visitantes) {
            if (v.getNombre().equalsIgnoreCase(nombre)) {
                return v;
            }
        }
        return null;
    }

    public EspacioCultural buscarEspacio(String nombre) {
        for (EspacioCultural e : espacios) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    public EventoCultural buscarEvento(String nombre) {
        for (EventoCultural e : eventos) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    public void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
            return;
        }
        System.out.println("=== Reservas Registradas ===");
        for (Reserva r : reservas) {
            System.out.println(r);
        }
    }

    public void mostrarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos registrados.");
            return;
        }
        System.out.println("=== Eventos Culturales ===");
        for (EventoCultural e : eventos) {
            double ocupacion = Indicador.calcularOcupacion(e.getCapacidadActual(), e.getEspacio().getCapacidadMaxima());
            System.out.println(e + " - Ocupación: " + String.format("%.1f", ocupacion) + "%");
        }
    }

    public void generarReporte(String filtro) {
        List<String> datosReporte = new ArrayList<>();
        datosReporte.add("Fecha,Visitante,Tipo Visitante,Espacio,Capacidad Maxima");

        List<Reserva> reservasFiltradas = new ArrayList<>();
        if (filtro != null && !filtro.trim().isEmpty()) {

            for (Reserva reserva : reservas) {
                String filtroLower = filtro.toLowerCase();
                boolean coincide = reserva.getVisitante().getNombre().toLowerCase().contains(filtroLower) ||
                                 reserva.getEspacio().getNombre().toLowerCase().contains(filtroLower) ||
                                 reserva.getVisitante().getTipo().toLowerCase().contains(filtroLower);
                if (coincide) {
                    reservasFiltradas.add(reserva);
                }
            }
        } else {
            // Si no hay filtro, agregar todas las reservas
            for (Reserva reserva : reservas) {
                reservasFiltradas.add(reserva);
            }
        }

        for (Reserva r : reservasFiltradas) {
            String linea = String.format("%s,%s,%s,%s,%d",
                    r.getFecha(),
                    r.getVisitante().getNombre(),
                    r.getVisitante().getTipo(),
                    r.getEspacio().getNombre(),
                    r.getEspacio().getCapacidadMaxima());
            datosReporte.add(linea);
        }

        // Crear objeto Reporte y exportar
        Reporte reporte = new Reporte(filtro, datosReporte);
        reporte.exportarCSV("reportes/reporte_reservas.csv");

        // También mostrar en consola
        System.out.println("=== Reporte de Reservas ===");
        if (filtro != null && !filtro.trim().isEmpty()) {
            System.out.println("Filtro aplicado: " + filtro);
        }
        for (Reserva r : reservasFiltradas) {
            System.out.printf("Fecha: %s | Visitante: %s (%s) | Espacio: %s%n",
                    r.getFecha(), r.getVisitante().getNombre(),
                    r.getVisitante().getTipo(), r.getEspacio().getNombre());
        }

        // Mostrar indicadores
        if (!reservasFiltradas.isEmpty()) {
            System.out.println("\n=== Indicadores  ===");
            for (EspacioCultural espacio : espacios) {
                int reservasEspacio = 0;
                for (Reserva reserva : reservasFiltradas) {
                    if (reserva.getEspacio().equals(espacio)) {
                        reservasEspacio++;
                    }
                }
                double ocupacionPromedio = Indicador.calcularOcupacion(reservasEspacio, espacio.getCapacidadMaxima());
                System.out.printf("Espacio: %s - Reservas: %d - Ocupación: %.1f%%%n",
                        espacio.getNombre(), reservasEspacio, ocupacionPromedio);
            }
        }
    }

    // Getters 
    public List<Visitante> getVisitantes() {
        return visitantes;
    }

    public List<EspacioCultural> getEspacios() {
        return espacios;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<EventoCultural> getEventos() {
        return eventos;
    }
}
