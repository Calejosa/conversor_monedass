package com.aluracursos.challange.convesordivisas.modelos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EjecutaConversiones {
    private final ApiRequestResponse api;

    public EjecutaConversiones() {
        this.api = new ApiRequestResponse(); // Se inicializa la API con este constructor
    }

    public void ejecutarOpcion(int opcion, Scanner scanner) {
        switch (opcion) {
            case 1 -> procesarConversion("USD", "EUR", scanner);
            case 2 -> procesarConversion("EUR", "USD", scanner);
            case 3 -> procesarConversion("USD", "BRL", scanner);
            case 4 -> procesarConversion("BRL", "USD", scanner);
            case 5 -> procesarConversion("USD", "CLP", scanner);
            case 6 -> procesarConversion("CLP", "USD", scanner);
            case 7 -> {
            } // Caso vacío, ya que el Main lo maneja
            default -> System.out.println("ERROR. Opción no válida, por favor intente de nuevo");
        }
    }

    private void procesarConversion(String monedaEntrada, String monedaSalida, Scanner scanner) {

        double tasaCambio = api.obtenerTasaCambio(monedaEntrada, monedaSalida);
        System.out.println("La tasa de cambio de " + monedaEntrada + " a " + monedaSalida + " es: " + tasaCambio);

        double cantidadDinero = 0;
        boolean entradaValida = false; // Bandera para verificar si la entrada es válida

        while (!entradaValida) {
            System.out.print("Ingrese la cantidad de dinero a convertir: ");

            try {
                cantidadDinero = scanner.nextDouble();
                entradaValida = true; // Entrada válida, salimos del bucle

            } catch (InputMismatchException e) {
                // Captura la entrada no válida y determina el tipo de error
                String entrada = scanner.next(); // Lee el valor inválido
                if (entrada.matches("[a-zA-Z]")) {
                    System.out.println("Por favor, no ingrese letras. Intente nuevamente.");
                } else if (entrada.matches("[!@#$%^&*()_+=<>?/;:'\"]")) {
                    System.out.println("Por favor, no ingrese signos. Intente nuevamente.");
                } else if (entrada.contains(".")) {
                    System.out.println("ERROR. Si desea ingresar una cantidad de dinero con número decimal, utilice la coma (,) en lugar del punto (.)");
                } else {
                    System.out.println("Por favor, ingrese una cantidad válida.");
                }
            }
            
            scanner.nextLine();
        }
        ConversorDinero conversor = new ConversorDinero(tasaCambio);
        double resultadoConversion = conversor.convertirDinero(cantidadDinero);

        System.out.printf("La cantidad convertida es: %.3f %s%n", resultadoConversion, monedaSalida);

        // Espera que el usuario presione una tecla antes de continuar
        esperarContinuacion(scanner);
    }

    private void esperarContinuacion(Scanner scanner) {
        System.out.print("Presione Enter para continuar...");
        scanner.nextLine();
    }
}
