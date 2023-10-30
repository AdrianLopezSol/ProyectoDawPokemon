/* 
ESTA CLASE SE HA UTILIZADO PARA REPOPULAR LA BASE DE DATOS, SE MANTIENE EN EL PROYECTO
PARA FUTURAS ADICIONES QUE AUN NO SE HAN HECHO.

package com.adrianLopez.proyectoPokemon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class PokemonDataImporter {

    public static void main(String[] args) {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/pokemon";
        String dbUser = "root";
        String dbPassword = "root";

        try {
            Connection dbConnection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            for (int id = 722; id <= 1017; id++) {
                String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + id;
                URL url = new URL(apiUrl);
                HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                httpConnection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response
                JSONObject jsonData = new JSONObject(response.toString());
                JSONArray typesArray = jsonData.getJSONArray("types");

                for (int i = 0; i < typesArray.length(); i++) {
                    System.out.println(typesArray.get(i));
                }

                
                // Insert data into the "pokemon_types" table
                for (int i = 0; i < typesArray.length(); i++) {
                    JSONObject typeData = typesArray.getJSONObject(i);
                    String typeName = typeData.getJSONObject("type").getString("name");
                    int typeId = getTypeID(dbConnection, typeName);
                    int slot = i + 1;

                    String insertQuery = "INSERT INTO pokemon_types (pok_id, type_id, slot) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = dbConnection.prepareStatement(insertQuery);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, typeId);
                    preparedStatement.setInt(3, slot);
                    preparedStatement.executeUpdate();
                }

            }

            dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getTypeID(Connection connection, String typeName) throws Exception {
        // Get the type_id from the "type" table
        String query = "SELECT type_id FROM types WHERE type_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, typeName);
        int typeId = -1; // Initialize with a value that indicates an error

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            typeId = resultSet.getInt("type_id");
        }

        return typeId;
    }
} */