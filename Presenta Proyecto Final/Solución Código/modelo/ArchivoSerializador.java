package modelo;

import java.io.*;
import java.util.ArrayList;

public class ArchivoSerializador {

    private final String carpeta = "data";
    private final String archivoVisitantes = carpeta + "/visitantes.dat";
    private final String archivoEspacios = carpeta + "/espacios.dat";
    private final String archivoReservas = carpeta + "/reservas.dat";
    private final String archivoEventos = carpeta + "/eventos.dat";

    public ArchivoSerializador() {
        crearCarpetaSiNoExiste();
    }

    private void crearCarpetaSiNoExiste() {
        File dir = new File(carpeta);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Visitantes
    public void guardarVisitantes(ArrayList<Visitante> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivoVisitantes))) {
            out.writeObject(lista);
        } catch (IOException e) {
            System.out.println(" Error al guardar en: " + archivoVisitantes);
        }
    }

    public ArrayList<Visitante> cargarVisitantes() {
        File f = new File(archivoVisitantes);
        if (!f.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivoVisitantes))) {
            return (ArrayList<Visitante>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Error al cargar archivo: " + archivoVisitantes);
            return new ArrayList<>();
        }
    }

    public void guardarEspacios(ArrayList<EspacioCultural> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivoEspacios))) {
            out.writeObject(lista);
        } catch (IOException e) {
            System.out.println(" Error al guardar en: " + archivoEspacios);
        }
    }

    public ArrayList<EspacioCultural> cargarEspacios() {
        File f = new File(archivoEspacios);
        if (!f.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivoEspacios))) {
            return (ArrayList<EspacioCultural>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("️ Error al cargar archivo: " + archivoEspacios);
            return new ArrayList<>();
        }
    }

    // Reservas
    public void guardarReservas(ArrayList<Reserva> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivoReservas))) {
            out.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Error al guardar en: " + archivoReservas);
        }
    }

    public ArrayList<Reserva> cargarReservas() {
        File f = new File(archivoReservas);
        if (!f.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivoReservas))) {
            return (ArrayList<Reserva>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("️ Error al cargar archivo: " + archivoReservas);
            return new ArrayList<>();
        }
    }

    // Eventos
    public void guardarEventos(ArrayList<EventoCultural> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivoEventos))) {
            out.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Error al guardar en: " + archivoEventos);
        }
    }

    public ArrayList<EventoCultural> cargarEventos() {
        File f = new File(archivoEventos);
        if (!f.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivoEventos))) {
            return (ArrayList<EventoCultural>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("️ Error al cargar archivo: " + archivoEventos);
            return new ArrayList<>();
        }
    }
}
