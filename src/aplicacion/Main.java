
package aplicacion;

import java.util.ArrayList;
import java.util.Scanner;
import modelo.Empleado;
import modelo.EmpleadoFijo;
import modelo.EmpleadoPorHora;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);

    private static final String[] meses = {
        "Enero", "Febrero", "Marzo", "Abril",
        "Mayo", "Junio", "Julio", "Agosto",
        "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    private static final ArrayList<Empleado> empleados = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;

        do {
            limpiarPantalla();
            System.out.println("===== SISTEMA DE NOMINA =====");
            System.out.println("1. Ingreso de nomina");
            System.out.println("2. Impresion de nomina");
            System.out.println("3. Salir");

            opcion = leerEnteroEnRango("Seleccione una opcion: ", 1, 3);

            switch (opcion) {
                case 1:
                    ingresarNomina();
                    break;
                case 2:
                    imprimirNomina();
                    break;
                case 3:
                    System.out.println("Gracias por utilizar el sistema.");
                    break;
            }

        } while (opcion != 3);

        teclado.close();
    }

    private static int solicitarMes() {
        System.out.println("\nSeleccione el mes de la nomina:");

        for (int i = 0; i < meses.length; i++) {
            System.out.println((i + 1) + ". " + meses[i]);
        }

        int numeroMes = leerEnteroEnRango("Numero de mes (1-12): ", 1, 12);

        return numeroMes;
    }

    private static void ingresarNomina() {
        limpiarPantalla();
        System.out.println("===== INGRESO DE NOMINA =====");

        int numeroMes = solicitarMes();
        String nombreMes = meses[numeroMes - 1];

        System.out.println("\nRegistrando nomina de " + nombreMes);

        String continuar;

        do {
            System.out.println("\nTipo de empleado:");
            System.out.println("1. Empleado fijo");
            System.out.println("2. Empleado por hora");

            int tipo = leerEnteroEnRango("Seleccione el tipo: ", 1, 2);

            if (tipo == 1) {
                registrarEmpleadoFijo();
            } else {
                registrarEmpleadoPorHora();
            }

            System.out.println("\nEmpleados registrados: " + empleados.size());
            System.out.print("Desea registrar otro empleado? (S/N): ");
            continuar = teclado.nextLine().trim();

            while (!continuar.equalsIgnoreCase("S")
                    && !continuar.equalsIgnoreCase("N")) {
                System.out.print("Respuesta invalida. Escriba S o N: ");
                continuar = teclado.nextLine().trim();
            }

        } while (continuar.equalsIgnoreCase("S"));

        System.out.println("\nNomina de " + nombreMes + " registrada.");
        pausar();
    }

    private static void registrarEmpleadoFijo() {
        System.out.println("\n--- DATOS DEL EMPLEADO FIJO ---");

        String dpi = leerDpi();
        String nombre = leerNombre();
        double salario = leerDoubleNoNegativo("Salario mensual (Q): ");
        double bonificacion = leerDoubleNoNegativo("Bonificacion mensual (Q): ");

        EmpleadoFijo empleado = new EmpleadoFijo(
                dpi, nombre, salario, bonificacion);

        empleados.add(empleado);
        System.out.println("Empleado fijo registrado correctamente.");
    }

    private static void registrarEmpleadoPorHora() {
        System.out.println("\n--- DATOS DEL EMPLEADO POR HORA ---");

        String dpi = leerDpi();
        String nombre = leerNombre();
        double pagoHora = leerDoubleNoNegativo("Pago por hora (Q): ");
        double horas = leerDoubleNoNegativo("Horas trabajadas: ");

        EmpleadoPorHora empleado = new EmpleadoPorHora(
                dpi, nombre, pagoHora, horas);

        empleados.add(empleado);
        System.out.println("Empleado por hora registrado correctamente.");
    }

    private static void imprimirNomina() {
        limpiarPantalla();
        System.out.println("===== IMPRESION DE NOMINA =====");

        int numeroMes = solicitarMes();
        String nombreMes = meses[numeroMes - 1];

        System.out.println("\n========== NOMINA DE " + nombreMes.toUpperCase() + " ==========");

        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados en la nomina.");
            System.out.println("Cantidad de empleados: 0");
            System.out.printf("Total de la nomina: Q %.2f%n", 0.0);
            pausar();
            return;
        }

        System.out.printf("%-15s %-25s %-15s %15s%n",
                "DPI", "Nombre", "Tipo", "Pago (Q)");
        System.out.println("--------------------------------------------------------------------------");

        double totalNomina = 0.0;

        for (Empleado empleado : empleados) {
            double pago = empleado.calcularPago();
            totalNomina += pago;

            System.out.printf("%-15s %-25s %-15s %15.2f%n",
                    empleado.getDpi(),
                    empleado.getNombre(),
                    empleado.obtenerTipo(),
                    pago);
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Cantidad de empleados: " + empleados.size());
        System.out.printf("Total de la nomina: Q %.2f%n", totalNomina);

        pausar();
    }

    private static String leerDpi() {
        while (true) {
            System.out.print("DPI (13 digitos): ");
            String dpi = teclado.nextLine().trim();

            if (dpi.matches("\\d{13}")) {
                return dpi;
            }

            System.out.println("Error: el DPI debe contener exactamente 13 digitos.");
        }
    }

    private static String leerNombre() {
        while (true) {
            System.out.print("Nombre completo: ");
            String nombre = teclado.nextLine().trim();

            if (!nombre.isEmpty()) {
                return nombre;
            }

            System.out.println("Error: el nombre no puede estar vacio.");
        }
    }

    private static int leerEnteroEnRango(
            String mensaje, int minimo, int maximo) {

        while (true) {
            System.out.print(mensaje);
            String entrada = teclado.nextLine().trim();

            try {
                int numero = Integer.parseInt(entrada);

                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }

                System.out.println("Error: ingrese un numero entre "
                        + minimo + " y " + maximo + ".");

            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un numero entero.");
            }
        }
    }

    private static double leerDoubleNoNegativo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = teclado.nextLine().trim().replace(',', '.');

            try {
                double numero = Double.parseDouble(entrada);

                if (Double.isFinite(numero) && numero >= 0) {
                    return numero;
                }

                System.out.println("Error: ingrese un numero valido mayor o igual a cero.");

            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un valor numerico valido.");
            }
        }
    }

    private static void limpiarPantalla() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
