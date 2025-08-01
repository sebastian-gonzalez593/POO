package vista;

import controlador.ControladorReservas;
import modelo.EspacioCultural;
import modelo.EventoCultural;
import modelo.Visitante;
import modelo.VisitanteEducativo;
import modelo.VisitanteIndividual;
import modelo.VisitanteTuristico;

import java.util.Scanner;

public class Main {
    private static ControladorReservas controlador = new ControladorReservas();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarVisitante();
                    break;
                case 2:
                    registrarEspacioCultural();
                    break;
                case 3:
                    registrarEventoCultural();
                    break;
                case 4:
                    registrarReserva();
                    break;
                case 5:
                    controlador.mostrarReservas();
                    break;
                case 6:
                    controlador.mostrarEventos();
                    break;
                case 7:
                    generarReporte();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Sistema de Reservas y Eventos Culturales ---");
        System.out.println("1. Registrar Visitante");
        System.out.println("2. Registrar Espacio Cultural");
        System.out.println("3. Registrar Evento Cultural");
        System.out.println("4. Registrar Reserva");
        System.out.println("5. Mostrar Reservas");
        System.out.println("6. Mostrar Eventos");
        System.out.println("7. Generar Reporte");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void registrarVisitante() {
        System.out.print("Nombre del visitante: ");
        String nombre = scanner.nextLine();

        System.out.print("Tipo de visitante (Individual, Educativo, Turístico): ");
        String tipoVisitante = scanner.nextLine().trim().toLowerCase();

        Visitante visitante;

        switch (tipoVisitante) {
            case "individual":
                visitante = new VisitanteIndividual(nombre);
                break;
            case "educativo":
                visitante = new VisitanteEducativo(nombre);
                break;
            case "turístico":
            case "turistico":
                visitante = new VisitanteTuristico(nombre);
                break;
            default:
                System.out.println("⚠️ Tipo inválido.");
                return;
        }

        controlador.agregarVisitante(visitante);
        System.out.println("Visitante registrado y guardado automáticamente.");
    }

    private static void registrarEspacioCultural() {
        System.out.print("Nombre del espacio cultural: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Capacidad máxima: ");
        int capacidad;
        try {
            capacidad = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Capacidad inválida.");
            return;
        }
        
        System.out.print("Ubicación: ");
        String ubicacion = scanner.nextLine();
        
        System.out.print("Horarios (ej: 9:00-18:00): ");
        String horarios = scanner.nextLine();
        if (horarios.trim().isEmpty()) {
            horarios = "9:00-18:00";
        }
        
        EspacioCultural espacio = new EspacioCultural(nombre, capacidad, ubicacion, horarios);
        
        // Agregar tipos de eventos
        System.out.print("¿Desea agregar tipos de eventos? (s/n): ");
        String agregarEventos = scanner.nextLine();
        if (agregarEventos.toLowerCase().startsWith("s")) {
            System.out.println("Ingrese tipos de eventos (escriba 'fin' para terminar):");
            String tipoEvento;
            while (!(tipoEvento = scanner.nextLine()).equalsIgnoreCase("fin")) {
                if (!tipoEvento.trim().isEmpty()) {
                    espacio.agregarTipoEvento(tipoEvento.trim());
                }
            }
        }
        
        controlador.agregarEspacio(espacio);
        System.out.println(" Espacio cultural registrado y guardado automáticamente.");
    }

    private static void registrarReserva() {
        // Mostrar tabla de espacios culturales disponibles
        mostrarTablaEspacios();
        
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();

        System.out.print("Nombre del espacio cultural: ");
        String nombreEspacio = scanner.nextLine();
        EspacioCultural espacio = controlador.buscarEspacio(nombreEspacio);
        if (espacio == null) {
            System.out.println("Espacio cultural no encontrado.");
            return;
        }

        System.out.print("Nombre del visitante: ");
        String nombreVisitante = scanner.nextLine();
        Visitante visitante = controlador.buscarVisitante(nombreVisitante);
        if (visitante == null) {
            System.out.println("Visitante no encontrado.");
            return;
        }

        controlador.registrarReserva(fecha, espacio, visitante);
        System.out.println("Reserva registrada y guardada automáticamente.");
    }

    private static void mostrarTablaEspacios() {
        System.out.println("\n === ESPACIOS CULTURALES DISPONIBLES ===");
        System.out.println("┌────────────────────────────┬─────────┬────────────────────┬─────────────┐");
        System.out.println("│ Nombre del Espacio         │ Capac.  │ Ubicación          │ Horarios    │");
        System.out.println("├────────────────────────────┼─────────┼────────────────────┼─────────────┤");
        
        if (controlador.getEspacios().isEmpty()) {
            System.out.println("│ No hay espacios registrados                                                │");
        } else {
            for (EspacioCultural espacio : controlador.getEspacios()) {
                System.out.printf("│ %-26s │ %7d │ %-18s │ %-11s │%n", 
                    truncarTexto(espacio.getNombre(), 26),
                    espacio.getCapacidadMaxima(),
                    truncarTexto(espacio.getUbicacion(), 18),
                    truncarTexto(espacio.getHorarios(), 11));
            }
        }
        
        System.out.println("└────────────────────────────┴─────────┴────────────────────┴─────────────┘");
        System.out.println();
    }

    private static String truncarTexto(String texto, int maxLength) {
        if (texto.length() <= maxLength) {
            return texto;
        }
        return texto.substring(0, maxLength - 3) + "...";
    }

    private static void registrarEventoCultural() {
        System.out.print("Nombre del evento cultural: ");
        String nombreEvento = scanner.nextLine();
        
        System.out.print("Fecha del evento (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        
        System.out.print("Nombre del espacio cultural: ");
        String nombreEspacio = scanner.nextLine();
        EspacioCultural espacio = controlador.buscarEspacio(nombreEspacio);
        if (espacio == null) {
            System.out.println(" Espacio cultural no encontrado.");
            return;
        }
        
        System.out.print("¿Requiere reserva? (s/n): ");
        String requiereReservaStr = scanner.nextLine();
        boolean requiereReserva = requiereReservaStr.toLowerCase().startsWith("s");
        
        try {
            EventoCultural evento = new EventoCultural(nombreEvento, fechaStr, espacio, requiereReserva);
            controlador.agregarEvento(evento);
            System.out.println(" Evento cultural registrado y guardado automáticamente.");
        } catch (Exception e) {
            System.out.println("️ Error en la fecha del evento: " + e.getMessage());
        }
    }

    private static void generarReporte() {
        System.out.print("Ingrese filtro para reporte (deje vacío para todos): ");
        String filtro = scanner.nextLine();
        controlador.generarReporte(filtro);
    }
}
