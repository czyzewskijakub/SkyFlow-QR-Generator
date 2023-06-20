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


    public static Connection connection ()  {
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

    public static ArrayList<Ticket> read(Connection connection,String email) {
        String query = "select * from tickets inner join users on tickets.user_id = users.user_id" +
                " inner join flights on tickets.flight_id = flights.flight_id where email='" +
                 email + "'";
        ArrayList<Ticket> userTickets = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setName(rs.getString("name"));
                ticket.setSurname(rs.getString("surname"));
                ticket.setId(rs.getString("identification_number"));
                ticket.setStart(rs.getString("departure_airport"));
                ticket.setEnd(rs.getString("arrival_airport"));
                ticket.setSeat(rs.getString("seat_number"));
                ticket.setFlightId(rs.getString("flight_id"));
                Log.d("DB", ticket.toString());
                userTickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userTickets;
    }
}

