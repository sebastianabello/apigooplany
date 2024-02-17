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


public class VentaDAO {
    private Connection connection;

    public VentaDAO() {
        connection = ConexionDB.getConnection();
    }

    public void create(Venta venta) {
        String sql = "INSERT INTO Ventas (id_mascota, id_cliente, fecha_venta, total) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, venta.getMascota().getIdMascota());
            statement.setInt(2, venta.getCliente().getIdCliente());
            statement.setDate(3, new java.sql.Date(venta.getFechaVenta().getTime()));
            statement.setDouble(4, venta.getTotal());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Venta> readAll() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Ventas";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(resultSet.getInt("id_venta"));
                Mascota mascota = new MascotaDAO().read(resultSet.getInt("id_mascota"));
                venta.setMascota(mascota);
                Cliente cliente = new ClienteDAO().read(resultSet.getInt("id_cliente"));
                venta.setCliente(cliente);
                venta.setFechaVenta(resultSet.getDate("fecha_venta"));
                venta.setTotal(resultSet.getDouble("total"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    public Venta read(int idVenta) {
        Venta venta = null;
        String sql = "SELECT * FROM Ventas WHERE id_venta = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idVenta);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                venta = new Venta();
                venta.setIdVenta(resultSet.getInt("id_venta"));
                Mascota mascota = new MascotaDAO().read(resultSet.getInt("id_mascota"));
                venta.setMascota(mascota);
                Cliente cliente = new ClienteDAO().read(resultSet.getInt("id_cliente"));
                venta.setCliente(cliente);
                venta.setFechaVenta(resultSet.getDate("fecha_venta"));
                venta.setTotal(resultSet.getDouble("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venta;
    }

    public void update(Venta venta) {
        String sql = "UPDATE Ventas SET id_mascota = ?, id_cliente = ?, fecha_venta = ?, total = ? WHERE id_venta = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, venta.getMascota().getIdMascota());
            statement.setInt(2, venta.getCliente().getIdCliente());
            statement.setDate(3, new java.sql.Date(venta.getFechaVenta().getTime()));
            statement.setDouble(4, venta.getTotal());
            statement.setInt(5, venta.getIdVenta());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int idVenta) {
        String sql = "DELETE FROM Ventas WHERE id_venta = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idVenta);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void guardar(Venta venta) {
        String sql = "INSERT INTO ventas (cliente_id) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, venta.getCliente().getIdCliente());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
