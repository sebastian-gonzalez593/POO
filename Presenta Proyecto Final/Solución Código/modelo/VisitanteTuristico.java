package modelo;

public class VisitanteTuristico extends Visitante {
    public VisitanteTuristico(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "Turístico";
    }
}
