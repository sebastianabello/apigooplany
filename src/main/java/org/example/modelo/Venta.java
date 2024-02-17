package org.example.modelo;

import java.util.Date;

public class Venta {
    private int idVenta;
    private Mascota mascota;
    private Cliente cliente;
    private Date fechaVenta;
    private double total;

    public Venta() {
    }

    public Venta(int idVenta, Mascota mascota, Cliente cliente, Date fechaVenta, double total) {
        this.idVenta = idVenta;
        this.mascota = mascota;
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
        this.total = total;
    }

    public Venta(Cliente cliente) {

    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
