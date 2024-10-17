package com.aluracursos.challange.convesordivisas.modelos;

public class ConversorDinero {

    private double tasaCambio;

    public ConversorDinero(double tasaCambio) {
        this.tasaCambio = tasaCambio;
    }

    public double convertirDinero(double cantidad) {
        return cantidad * tasaCambio;
    }
}
