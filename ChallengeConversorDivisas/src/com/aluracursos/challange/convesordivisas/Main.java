package com.aluracursos.challange.convesordivisas;

import com.aluracursos.challange.convesordivisas.modelos.EjecutaConversiones;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EjecutaConversiones menu = new EjecutaConversiones();
        int opcion = 0;

        do {
            mostrarMenu();
            
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion != 7) {
                    menu.ejecutarOpcion(opcion, scanner);
                }

            } else {
                System.out.println("ERROR. Por favor ingrese una opción válida.");
                scanner.nextLine();
            }

        } while (opcion != 7);

        System.out.println("Finalizando programa, muchas gracias por usar nuestros servicios");
        scanner.close();
    }

    static void mostrarMenu() {
        System.out.println("Bienvenido al conversor de divisas");
        System.out.println("1. Convertir Dólar a Euro");
        System.out.println("2. Convertir Euro a Dólar");
        System.out.println("3. Convertir Dólar a Real Brasileño");
        System.out.println("4. Convertir Real Brasileño a Dólar");
        System.out.println("5. Convertir Dólar a Peso Chileno");
        System.out.println("6. Convertir Peso Chileno a Dólar");
        System.out.println("7. Salir");
    }
}