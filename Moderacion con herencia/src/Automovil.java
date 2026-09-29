public class Automovil extends Vehiculo {
    private int pasajeros;
    private boolean esAutomatico;

    public Automovil(String placa, String marca, String modelo, double tarifaDiaria, int pasajeros, boolean esAutomatico) {
        super(placa, marca, modelo, tarifaDiaria);
        if (pasajeros <= 0) {
            throw new IllegalArgumentException("El número de pasajeros debe ser mayor a 0.");
        }
        this.pasajeros = pasajeros;
        this.esAutomatico = esAutomatico;
    }

    public int getPasajeros() { return pasajeros; }
    public boolean isEsAutomatico() { return esAutomatico; }

    @Override
    public double calcularCosto(int dias) {
        if (dias <= 0) throw new IllegalArgumentException("Los días deben ser mayores a 0.");
        double recargoDiario = esAutomatico ? 50.0 : 0.0;
        return (tarifaDiaria + recargoDiario) * dias;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Pasajeros: %d | Transmisión: %s",
                pasajeros, (esAutomatico ? "Automática" : "Manual"));
    }
}