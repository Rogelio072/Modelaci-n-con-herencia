public abstract class Vehiculo {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected double tarifaDiaria;
    protected boolean disponible;

    public Vehiculo(String placa, String marca, String modelo, double tarifaDiaria) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede estar vacía.");
        }
        if (tarifaDiaria <= 0) {
            throw new IllegalArgumentException("La tarifa diaria debe ser mayor a 0.");
        }
        this.placa = placa.toUpperCase().trim();
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = true; // Todo vehículo inicia disponible
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public double getTarifaDiaria() { return tarifaDiaria; }
    public boolean isDisponible() { return disponible; }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Método polimórfico a implementar por cada subclase
    public abstract double calcularCosto(int dias);

    public String getTipo() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return String.format("Placa: %s | Marca: %s | Modelo: %s | Tarifa Diaria: Q%.2f | Estado: %s",
                placa, marca, modelo, tarifaDiaria, (disponible ? "Disponible" : "Alquilado"));
    }
}