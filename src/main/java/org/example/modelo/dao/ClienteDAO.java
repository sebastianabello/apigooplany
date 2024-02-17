package org.example.modelo.dao;

import org.example.modelo.Cliente;
import org.example.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {
    private Connection connection;
    public ClienteDAO() {
        connection = ConexionDB.getConnection();
    }
    public void create(Cliente cliente) {
        String sql = "INSERT INTO Clientes (nombre, telefono, correo) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getTelefono());
            statement.setString(3, cliente.getCorreo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Cliente> readAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setCorreo(resultSet.getString("correo"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
    public Cliente read(int idCliente) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Clientes WHERE id_cliente = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idCliente);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setCorreo(resultSet.getString("correo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
    public void update(Cliente cliente) {
        String sql = "UPDATE Clientes SET nombre = ?, telefono = ?, correo = ? WHERE id_cliente = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getTelefono());
            statement.setString(3, cliente.getCorreo());
            statement.setInt(4, cliente.getIdCliente());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idCliente) {
        String sql = "DELETE FROM Clientes WHERE id_cliente = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idCliente);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void guardar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombre());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
