package org.example.modelo.dao;

import org.example.modelo.Cliente;
import org.example.modelo.Mascota;
import org.example.modelo.Venta;
import org.example.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO implements Almacenamiento {
    private Connection connection;

    public VentaDAO() {
        connection = ConexionDB.getConnection();
    }

    @Override
    public void create(Object obj) {
        Venta venta = (Venta) obj;
        String sql = "INSERT INTO Ventas (id_cliente, id_mascota, fecha) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, venta.getCliente().getIdCliente());
            statement.setInt(2, venta.getMascota().getIdMascota());
            statement.setDate(3, new java.sql.Date(venta.getFecha().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object read(int id) {
        Venta venta = null;
        String sql = "SELECT * FROM Ventas WHERE id_venta = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                venta = new Venta();
                venta.setIdVenta(resultSet.getInt("id_venta"));
                Cliente cliente = new ClienteDAO().read(resultSet.getInt("id_cliente"));
                venta.setCliente(cliente);
                Mascota mascota = new MascotaDAO().read(resultSet.getInt("id_mascota"));
                venta.setMascota(mascota);
                venta.setFecha(resultSet.getDate("fecha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venta;
    }

    @Override
    public void update(Object obj) {
        Venta venta = (Venta) obj;
        String sql = "UPDATE Ventas SET id_cliente = ?, id_mascota = ?, fecha = ? WHERE id_venta = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, venta.getCliente().getIdCliente());
            statement.setInt(2, venta.getMascota().getIdMascota());
            statement.setDate(3, new java.sql.Date(venta.getFecha().getTime()));
            statement.setInt(4, venta.getIdVenta());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Ventas WHERE id_venta = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}