public class Motocicleta extends Vehiculo {
    private int cilindraje;

    public Motocicleta(String placa, String marca, String modelo, double tarifaDiaria, int cilindraje) {
        super(placa, marca, modelo, tarifaDiaria);
        if (cilindraje <= 0) {
            throw new IllegalArgumentException("El cilindraje debe ser mayor a 0.");
        }
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() { return cilindraje; }

    @Override
    public double calcularCosto(int dias) {
        if (dias <= 0) throw new IllegalArgumentException("Los días deben ser mayores a 0.");
        double recargoUnico = (cilindraje > 250) ? 75.0 : 0.0;
        return (tarifaDiaria * dias) + recargoUnico;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Cilindraje: %d cc", cilindraje);
    }
}