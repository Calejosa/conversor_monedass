package com.aluracursos.challange.convesordivisas.modelos;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequestResponse {

    private static final String KeyApi = "179d23e79bd04b8a5ec9f5c9";

    public double obtenerTasaCambio(String monedaOrigen, String monedaDestino) {

        String monedaOrigenCodificada;
        String monedaDestinoCodificada;
        try {
            monedaOrigenCodificada = URLEncoder.encode(monedaOrigen, "UTF-8");
            monedaDestinoCodificada = URLEncoder.encode(monedaDestino, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        }

        String url = "https://v6.exchangerate-api.com/v6/" + KeyApi + "/pair/" + monedaOrigenCodificada + "/" + monedaDestinoCodificada;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            // Deserializar el JSON a un objeto JAVA del tipo TasaDeCambio
            TasaDeCambio tasaDeCambio = new Gson().fromJson(json, TasaDeCambio.class);

            // Obtener el valor de la tasa de cambio
            return tasaDeCambio.getConversionRate();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
