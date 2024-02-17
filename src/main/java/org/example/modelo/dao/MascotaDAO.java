package org.example.modelo.dao;

import org.example.modelo.Mascota;
import org.example.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MascotaDAO {
    private Connection connection;
    public MascotaDAO() {
        connection = ConexionDB.getConnection();
    }

    public void create(Mascota mascota) {
        String sql = "INSERT INTO Mascotas (nombre, especie, raza, edad, precio) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mascota.getNombre());
            statement.setString(2, mascota.getEspecie());
            statement.setString(3, mascota.getRaza());
            statement.setInt(4, mascota.getEdad());
            statement.setDouble(5, mascota.getPrecio());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mascota> readAll() {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT * FROM Mascotas";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Mascota mascota = new Mascota();
                mascota.setIdMascota(resultSet.getInt("id_mascota"));
                mascota.setNombre(resultSet.getString("nombre"));
                mascota.setEspecie(resultSet.getString("especie"));
                mascota.setRaza(resultSet.getString("raza"));
                mascota.setEdad(resultSet.getInt("edad"));
                mascota.setPrecio(resultSet.getDouble("precio"));
                mascotas.add(mascota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascotas;
    }

    public Mascota read(int idMascota) {
        Mascota mascota = null;
        String sql = "SELECT * FROM Mascotas WHERE id_mascota = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idMascota);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                mascota = new Mascota();
                mascota.setIdMascota(resultSet.getInt("id_mascota"));
                mascota.setNombre(resultSet.getString("nombre"));
                mascota.setEspecie(resultSet.getString("especie"));
                mascota.setRaza(resultSet.getString("raza"));
                mascota.setEdad(resultSet.getInt("edad"));
                mascota.setPrecio(resultSet.getDouble("precio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascota;
    }
    public void update(Mascota mascota) {
        String sql = "UPDATE Mascotas SET nombre = ?, especie = ?, raza = ?, edad = ?, precio = ? WHERE id_mascota = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mascota.getNombre());
            statement.setString(2, mascota.getEspecie());
            statement.setString(3, mascota.getRaza());
            statement.setInt(4, mascota.getEdad());
            statement.setDouble(5, mascota.getPrecio());
            statement.setInt(6, mascota.getIdMascota());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int idMascota) {
        String sql = "DELETE FROM Mascotas WHERE id_mascota = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idMascota);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
