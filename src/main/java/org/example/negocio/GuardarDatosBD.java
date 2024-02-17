package org.example.negocio;

import org.example.modelo.Cliente;
import org.example.modelo.Venta;
import org.example.modelo.dao.ClienteDAO;
import org.example.modelo.dao.VentaDAO;

public class GuardarDatosBD {
    private final ClienteDAO clienteDAO;
    private final VentaDAO ventaDAO;

    public GuardarDatosBD() {
        this.clienteDAO = new ClienteDAO();
        this.ventaDAO = new VentaDAO();
    }

    public void guardarDatos(String nombre) {
        Cliente cliente = new Cliente(nombre);
        clienteDAO.guardar(cliente);

        Venta venta = new Venta(cliente);
        ventaDAO.guardar(venta);
    }
}
