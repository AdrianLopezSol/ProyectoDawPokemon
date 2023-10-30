package com.adrianLopez.proyectoPokemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

// ... (other imports and code)

public class PokemonDataImporter {

    public static void main(String[] args) {
        // Set up your database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/pokemon";
        String username = "root";
        String password = "root";

        try (Connection databaseConnection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Loop through Pokémon IDs
            for (int pokemonId = 722; pokemonId <= 1017; pokemonId++) {
                // Make an HTTP request to the PokeAPI
                URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + pokemonId);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");

                if (httpURLConnection.getResponseCode() == 200) {
                    // Read the response
                    BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder responseBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseBuilder.append(line);
                    }

                    // Parse the JSON response
                    JSONObject json = new JSONObject(responseBuilder.toString());

                    // Extract the stats you need from the "stats" array
                    JSONArray stats = json.getJSONArray("stats");
                    int hp = stats.getJSONObject(0).getInt("base_stat");  // HP is the first stat in the array
                    int attack = stats.getJSONObject(1).getInt("base_stat");
                    int defense = stats.getJSONObject(2).getInt("base_stat");
                    int sp_attack = stats.getJSONObject(3).getInt("base_stat");
                    int sp_defense = stats.getJSONObject(4).getInt("base_stat");
                    int speed = stats.getJSONObject(5).getInt("base_stat");  // Attack is the second stat
                    // Extract other stats in a similar way

                    // Insert the stats into your database
                    String insertSql = "INSERT INTO base_stats (pok_id, b_hp, b_atk, b_def, b_sp_atk, b_sp_def, b_speed) VALUES (?, ?, ?, ?, ? ,?, ?)";
                    try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(insertSql)) {
                        preparedStatement.setInt(1, pokemonId);
                        preparedStatement.setInt(2, hp);
                        preparedStatement.setInt(3, attack);
                        preparedStatement.setInt(4, defense);
                        preparedStatement.setInt(5, sp_attack);
                        preparedStatement.setInt(6, sp_defense);
                        preparedStatement.setInt(7, speed);
                        // Set parameters for other stats
                        preparedStatement.executeUpdate();
                    }
                }
                httpURLConnection.disconnect();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}