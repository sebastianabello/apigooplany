package org.example.modelo;

import java.util.Date;

public class Venta {
    private int idVenta;
    private Cliente cliente;
    private Mascota mascota;
    private Date fecha;

    public Venta() {
    }

    public Venta(int idVenta, Cliente cliente, Mascota mascota, Date fecha) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.mascota = mascota;
        this.fecha = fecha;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}