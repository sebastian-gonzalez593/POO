package modelo;

public class VisitanteEducativo extends Visitante {
    public VisitanteEducativo(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "Educativo";
    }
}
