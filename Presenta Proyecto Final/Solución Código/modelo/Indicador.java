package modelo;

import java.time.LocalDate;
import java.util.List;

public class Indicador {

    // Sobrecarga: Calcula ocupación básica con números
    public static double calcularOcupacion(int visitasConfirmadas, int aforoMaximo) {
        if (aforoMaximo == 0) return 0;
        return (double) visitasConfirmadas / aforoMaximo * 100;
    }

    // Sobrecarga: Calcula ocupación de un espacio específico
    public static double calcularOcupacion(EspacioCultural espacio, List<Reserva> reservas) {
        int visitasConfirmadas = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getEspacio().equals(espacio)) {
                visitasConfirmadas++;
            }
        }
        return calcularOcupacion(visitasConfirmadas, espacio.getCapacidadMaxima());
    }

    // Sobrecarga: Calcula ocupación de un espacio en una fecha específica
    public static double calcularOcupacion(EspacioCultural espacio, LocalDate fecha, List<Reserva> reservas) {
        int visitasConfirmadas = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getEspacio().equals(espacio) && reserva.getFecha().equals(fecha)) {
                visitasConfirmadas++;
            }
        }
        return calcularOcupacion(visitasConfirmadas, espacio.getCapacidadMaxima());
    }

    // Sobrecarga: Calcula frecuencia básica
    public static double calcularFrecuencia(int visitasRealizadas, int mesesActivos) {
        if (mesesActivos == 0) return 0;
        return (double) visitasRealizadas / mesesActivos;
    }

    // Sobrecarga: Calcula frecuencia de un visitante específico
    public static double calcularFrecuencia(Visitante visitante, List<Reserva> reservas, int mesesActivos) {
        int visitasRealizadas = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getVisitante().equals(visitante)) {
                visitasRealizadas++;
            }
        }
        return calcularFrecuencia(visitasRealizadas, mesesActivos);
    }

    // Método adicional: Promedio de ocupación de todos los espacios
    public static double calcularOcupacionPromedio(List<EspacioCultural> espacios, List<Reserva> reservas) {
        if (espacios.isEmpty()) return 0;
        
        double sumaOcupaciones = 0;
        for (EspacioCultural espacio : espacios) {
            sumaOcupaciones += calcularOcupacion(espacio, reservas);
        }
        
        return sumaOcupaciones / espacios.size();
    }
}
