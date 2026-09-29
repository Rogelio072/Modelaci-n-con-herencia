import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorFlota gestor = new GestorFlota();
        Scanner scanner = new Scanner(System.in);

        // Cargamos los datos iniciales
        gestor.agregarVehiculo(new Automovil("P123ABC", "Toyota", "Yaris", 150.0, 5, false));
        gestor.agregarVehiculo(new Automovil("P456DEF", "Honda", "Civic", 200.0, 5, true));

        gestor.agregarVehiculo(new Motocicleta("M111AAA", "Yamaha", "YBR125", 80.0, 125));
        gestor.agregarVehiculo(new Motocicleta("M222BBB", "Kawasaki", "Ninja 400", 120.0, 399));

        gestor.agregarVehiculo(new CamionetaCarga("C333CCC", "Isuzu", "NPR", 200.0, 1.5));
        gestor.agregarVehiculo(new CamionetaCarga("C444DDD", "Hino", "Dutro", 300.0, 3.0));

        int opcion = 0;

        while (opcion != 7) {
            System.out.println("\n--- MENU RENTAMOVIL ---");
            System.out.println("1. Registrar vehiculo");
            System.out.println("2. Consultar flota");
            System.out.println("3. Cotizar alquiler");
            System.out.println("4. Confirmar alquiler");
            System.out.println("5. Registrar devolucion");
            System.out.println("6. Ver reporte general");
            System.out.println("7. Salir");
            System.out.print("Ingrese una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Opcion invalida, debe ser un numero.");
                continue;
            }

            if (opcion == 1) {
                System.out.println("\n-- Registrar Vehiculo --");
                System.out.println("1. Automovil");
                System.out.println("2. Motocicleta");
                System.out.println("3. Camioneta de carga");
                System.out.print("Seleccione el tipo: ");
                
                int tipo = 0;
                try {
                    tipo = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Debe ingresar un numero valido.");
                    continue;
                }

                if (tipo < 1 || tipo > 3) {
                    System.out.println("Tipo no valido.");
                    continue;
                }

                System.out.print("Ingrese la placa: ");
                String placa = scanner.nextLine().trim();

                if (placa.isEmpty()) {
                    System.out.println("La placa no puede estar vacia.");
                    continue;
                }

                if (gestor.buscarPorPlaca(placa) != null) {
                    System.out.println("Error: Ya existe un vehiculo con esa placa.");
                    continue;
                }

                System.out.print("Ingrese la marca: ");
                String marca = scanner.nextLine();
                System.out.print("Ingrese el modelo: ");
                String modelo = scanner.nextLine();
                
                System.out.print("Ingrese la tarifa diaria: ");
                double tarifa = 0;
                try {
                    tarifa = Double.parseDouble(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Tarifa invalida.");
                    continue;
                }

                if (tarifa <= 0) {
                    System.out.println("La tarifa debe ser mayor a 0.");
                    continue;
                }

                if (tipo == 1) {
                    System.out.print("Ingrese la cantidad de pasajeros: ");
                    int pasajeros = Integer.parseInt(scanner.nextLine());
                    System.out.print("¿Es automatica? (s/n): ");
                    String respAuto = scanner.nextLine();
                    boolean esAuto = respAuto.equalsIgnoreCase("s");

                    gestor.agregarVehiculo(new Automovil(placa, marca, modelo, tarifa, pasajeros, esAuto));
                    System.out.println("Automovil registrado con exito.");

                } else if (tipo == 2) {
                    System.out.print("Ingrese el cilindraje: ");
                    int cc = Integer.parseInt(scanner.nextLine());

                    gestor.agregarVehiculo(new Motocicleta(placa, marca, modelo, tarifa, cc));
                    System.out.println("Motocicleta registrada con exito.");

                } else if (tipo == 3) {
                    System.out.print("Ingrese la capacidad en toneladas: ");
                    double cap = Double.parseDouble(scanner.nextLine());

                    gestor.agregarVehiculo(new CamionetaCarga(placa, marca, modelo, tarifa, cap));
                    System.out.println("Camioneta registrada con exito.");
                }

            } else if (opcion == 2) {
                System.out.println("\n-- Lista de Vehiculos --");
                for (int i = 0; i < gestor.getFlota().size(); i++) {
                    Vehiculo v = gestor.getFlota().get(i);
                    System.out.println(v.toString());
                }

            } else if (opcion == 3) {
                System.out.println("\n-- Cotizar Alquiler --");
                System.out.print("Ingrese la placa del vehiculo: ");
                String placa = scanner.nextLine();

                Vehiculo v = gestor.buscarPorPlaca(placa);
                if (v == null) {
                    System.out.println("No se encontro el vehiculo.");
                } else {
                    System.out.print("Ingrese la cantidad de dias: ");
                    int dias = Integer.parseInt(scanner.nextLine());

                    if (dias <= 0) {
                        System.out.println("Los dias deben ser mayores a 0.");
                    } else {
                        double total = v.calcularCosto(dias);
                        System.out.println(v.toString());
                        System.out.println("Dias: " + dias);
                        System.out.printf("Costo Total: Q%.2f\n", total);
                    }
                }

            } else if (opcion == 4) {
                System.out.println("\n-- Confirmar Alquiler --");
                System.out.print("Ingrese la placa del vehiculo: ");
                String placa = scanner.nextLine();

                Vehiculo v = gestor.buscarPorPlaca(placa);
                if (v == null) {
                    System.out.println("No se encontro el vehiculo.");
                } else if (!v.isDisponible()) {
                    System.out.println("El vehiculo ya esta alquilado.");
                } else {
                    System.out.print("Ingrese la cantidad de dias: ");
                    int dias = Integer.parseInt(scanner.nextLine());

                    if (dias <= 0) {
                        System.out.println("Los dias deben ser mayores a 0.");
                    } else {
                        double total = v.calcularCosto(dias);
                        System.out.printf("El costo total sera de: Q%.2f\n", total);
                        System.out.print("¿Desea confirmar el alquiler? (s/n): ");
                        String conf = scanner.nextLine();

                        if (conf.equalsIgnoreCase("s")) {
                            gestor.confirmarAlquiler(placa, dias);
                            System.out.println("Alquiler realizado con exito.");
                        } else {
                            System.out.println("Alquiler cancelado.");
                        }
                    }
                }

            } else if (opcion == 5) {
                System.out.println("\n-- Registrar Devolucion --");
                System.out.print("Ingrese la placa del vehiculo a devolver: ");
                String placa = scanner.nextLine();

                Vehiculo v = gestor.buscarPorPlaca(placa);
                if (v == null) {
                    System.out.println("No existe un vehiculo con esa placa.");
                } else if (v.isDisponible()) {
                    System.out.println("Este vehiculo no esta alquilado, ya esta disponible.");
                } else {
                    gestor.registrarDevolucion(placa);
                    System.out.println("Devolucion realizada con exito.");
                }

            } else if (opcion == 6) {
                gestor.mostrarReporteGeneral();

            } else if (opcion == 7) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opción invalida.");
            }
        }

        scanner.close();
    }
}