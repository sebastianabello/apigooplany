package org.example.modelo.dao;

import org.example.modelo.Cliente;
import org.example.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements Almacenamiento {
    private Connection connection;

    public ClienteDAO() {
        connection = ConexionDB.getConnection();
    }

    @Override
    public void create(Object obj) {
        Cliente cliente = (Cliente) obj;
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

    @Override
    public Object read(int id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Clientes WHERE id_cliente = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
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

    @Override
    public void update(Object obj) {
        Cliente cliente = (Cliente) obj;
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

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Clientes WHERE id_cliente = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}