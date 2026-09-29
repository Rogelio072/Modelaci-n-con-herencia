import java.util.ArrayList;
import java.util.List;

public class GestorFlota {
    private List<Vehiculo> flota;
    private double ingresosAcumulados;

    public GestorFlota() {
        this.flota = new ArrayList<>();
        this.ingresosAcumulados = 0.0;
    }

    public double getIngresosAcumulados() {
        return ingresosAcumulados;
    }

    public boolean agregarVehiculo(Vehiculo vehiculo) {
        if (buscarPorPlaca(vehiculo.getPlaca()) != null) {
            return false; // Placa duplicada
        }
        flota.add(vehiculo);
        return true;
    }

    public Vehiculo buscarPorPlaca(String placa) {
        if (placa == null) return null;
        for (Vehiculo v : flota) {
            if (v.getPlaca().equalsIgnoreCase(placa.trim())) {
                return v;
            }
        }
        return null;
    }

    public List<Vehiculo> getFlota() {
        return flota;
    }

    public boolean confirmarAlquiler(String placa, int dias) {
        Vehiculo v = buscarPorPlaca(placa);
        if (v != null && v.isDisponible()) {
            double total = v.calcularCosto(dias);
            v.setDisponible(false);
            ingresosAcumulados += total; // Solo modifica ingresos al confirmar
            return true;
        }
        return false;
    }

    public boolean registrarDevolucion(String placa) {
        Vehiculo v = buscarPorPlaca(placa);
        if (v != null && !v.isDisponible()) {
            v.setDisponible(true);
            return true;
        }
        return false;
    }

    public void mostrarReporteGeneral() {
        int totalAutos = 0, autosDisp = 0, autosAlq = 0;
        int totalMotos = 0, motosDisp = 0, motosAlq = 0;
        int totalCarga = 0, cargaDisp = 0, cargaAlq = 0;

        for (Vehiculo v : flota) {
            if (v instanceof Automovil) {
                totalAutos++;
                if (v.isDisponible()) autosDisp++; else autosAlq++;
            } else if (v instanceof Motocicleta) {
                totalMotos++;
                if (v.isDisponible()) motosDisp++; else motosAlq++;
            } else if (v instanceof CamionetaCarga) {
                totalCarga++;
                if (v.isDisponible()) cargaDisp++; else cargaAlq++;
            }
        }

        System.out.println("\n===== REPORTE GENERAL DE LA FLOTA =====");
        System.out.println("Total de vehículos registrados: " + flota.size());
        System.out.printf("Automóviles     - Total: %d | Disponibles: %d | Alquilados: %d\n", totalAutos, autosDisp, autosAlq);
        System.out.printf("Motocicletas    - Total: %d | Disponibles: %d | Alquilados: %d\n", totalMotos, motosDisp, motosAlq);
        System.out.printf("Camionetas Carga- Total: %d | Disponibles: %d | Alquilados: %d\n", totalCarga, cargaDisp, cargaAlq);
        System.out.printf("---------------------------------------\n");
        System.out.printf("Dinero Total Acumulado: Q%.2f\n", ingresosAcumulados);
        System.out.println("=======================================\n");
    }
}