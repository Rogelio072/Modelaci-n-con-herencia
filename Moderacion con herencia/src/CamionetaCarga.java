public class CamionetaCarga extends Vehiculo {
    private double capacidadToneladas;

    public CamionetaCarga(String placa, String marca, String modelo, double tarifaDiaria, double capacidadToneladas) {
        super(placa, marca, modelo, tarifaDiaria);
        if (capacidadToneladas <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0.");
        }
        this.capacidadToneladas = capacidadToneladas;
    }

    public double getCapacidadToneladas() { return capacidadToneladas; }

    @Override
    public double calcularCosto(int dias) {
        if (dias <= 0) throw new IllegalArgumentException("Los días deben ser mayores a 0.");
        double recargoDiario = 100.0 * capacidadToneladas;
        return (tarifaDiaria + recargoDiario) * dias;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Capacidad: %.2f Toneladas", capacidadToneladas);
    }
}