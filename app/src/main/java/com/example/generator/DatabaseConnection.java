package com.example.generator;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://skyflow-db.postgres.database.azure.com:5432/postgres?user=postgres&password=Password123&sslmode=require";


    public static Connection connection() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Ticket> read(Connection connection, String email) {
        String query = "select * from tickets inner join users on tickets.user_id = users.user_id" +
                " inner join flights on tickets.flight_id = flights.flight_id where email='" +
                email + "'";
        ArrayList<Ticket> userTickets = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Ticket ticket = new Ticket.TicketBuilder()
                        .setName(rs.getString("name"))
                        .setSurname(rs.getString("surname"))
                        .setId(rs.getString("identification_number"))
                        .setStart(rs.getString("departure_airport"))
                        .setDate(rs.getString("departure_date"))
                        .setTicketId(rs.getString("ticket_id"))
                        .setEnd(rs.getString("arrival_airport"))
                        .setSeat(rs.getString("seat_number"))
                        .setFlightId(rs.getString("flight_id")).build();
                userTickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userTickets;
    }
}

