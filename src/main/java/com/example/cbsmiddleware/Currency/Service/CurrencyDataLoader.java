package com.example.cbsmiddleware.Currency.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CurrencyDataLoader {

    public static void main(String[] args) {
        String jsonString = "{...}"; // Votre JSON ici

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, List<Map<String, Object>>> data = objectMapper.readValue(jsonString, new TypeReference<Map<String, List<Map<String, Object>>>>() {});

            List<Map<String, Object>> currencyOptions = data.get("currencyOptions");

            // Connexion à la base de données
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/votre_base_de_donnees", "utilisateur", "mot_de_passe");

            // Préparation de la requête d'insertion
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Currency (code, name, decimalPlaces, displaySymbol, nameCode, displayLabel) VALUES (?, ?, ?, ?, ?, ?)");

            // Parcours des devises et insertion dans la base de données
            for (Map<String, Object> currency : currencyOptions) {
                preparedStatement.setString(1, (String) currency.get("code"));
                preparedStatement.setString(2, (String) currency.get("name"));
                preparedStatement.setInt(3, (int) currency.get("decimalPlaces"));
                preparedStatement.setString(4, (String) currency.get("displaySymbol"));
                preparedStatement.setString(5, (String) currency.get("nameCode"));
                preparedStatement.setString(6, (String) currency.get("displayLabel"));
                preparedStatement.executeUpdate();
            }

            // Fermeture des ressources
            preparedStatement.close();
            connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}