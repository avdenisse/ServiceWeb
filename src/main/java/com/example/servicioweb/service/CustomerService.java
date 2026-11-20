package com.example.servicioweb.service;

import com.example.servicioweb.model.Customers;
import com.example.servicioweb.model.Usuario;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private LocalDateTime convertirFecha(String fecha) {
        if (fecha == null || fecha.isBlank()) {
            return null;
        }

        return OffsetDateTime.parse(fecha).toLocalDateTime();
    }
    private static final String URL =
            "https://sqpvqkkdfgtzdgrjrgxs.supabase.co/rest/v1/customers";

    // Obtener la API Key desde una variable de entorno
    private static final String API_KEY =
            System.getenv("apiKey");

    public List<Customers> getCustomers()
            throws IOException, InterruptedException {

        if (API_KEY == null || API_KEY.isBlank()) {
            throw new IllegalStateException(
                    "No se ha configurado la API Key de Supabase"
            );
        }

        // Crear cliente HTTP
        HttpClient cliente = HttpClient.newHttpClient();

        // Construir solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .timeout(Duration.ofSeconds(15))
                .header("apikey", API_KEY)
                .header("Authorization", "Bearer " + API_KEY)
                .header("Accept", "application/json")
                .GET()
                .build();

        // Enviar solicitud
        HttpResponse<String> response = cliente.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        // Verificar respuesta
        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Error al obtener usuarios: "
                            + response.statusCode()
                            + " - " + response.body()
            );
        }

        // Convertir respuesta JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode datos = mapper.readTree(response.body());

        if (!datos.isArray()) {
            throw new RuntimeException(
                    "La respuesta no contiene un formato JSON válido"
            );
        }

        // Crear lista de clientes
        List<Customers> customers = new ArrayList<>();

        for (JsonNode nodo : datos) {

            Customers customer = new Customers(
                    nodo.path("id").asInt(),
                    nodo.path("first_name").asText(),
                    nodo.path("last_name").asText(),
                    nodo.path("email").asText(),
                    nodo.path("phone").asText(),
                    LocalDate.parse(nodo.get("date_of_birth").asText()),
                    nodo.path("country").asText(),
                    nodo.path("city").asText(),
                    nodo.path("address").asText(),
                    nodo.path("annual_income").asDouble(),
                    nodo.path("credit_score").asInt(),
                    nodo.path("has_unpaid_debt").asBoolean(),
                    nodo.path("unpaid_debt_amount").asDouble(),
                    nodo.path("status").asText(),
                    convertirFecha(nodo.get("created_at").asText()),
                    convertirFecha(nodo.get("updated_at").asText())

            );

            customers.add(customer);
        }

        return customers;
    }
}
