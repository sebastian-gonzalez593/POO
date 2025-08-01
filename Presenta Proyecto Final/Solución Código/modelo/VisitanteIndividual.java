package modelo;

public class VisitanteIndividual extends Visitante {
    public VisitanteIndividual(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "Individual";
    }
}
