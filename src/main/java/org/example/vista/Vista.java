package org.example.vista;

import org.example.modelo.Cliente;
import org.example.modelo.Mascota;
import org.example.modelo.Venta;
import org.example.modelo.dao.ClienteDAO;
import org.example.modelo.dao.MascotaDAO;
import org.example.modelo.dao.VentaDAO;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Vista {
    private Scanner scanner;
    private ClienteDAO clienteDAO;
    private MascotaDAO mascotaDAO;
    private VentaDAO ventaDAO;

    public Vista() {
        scanner = new Scanner(System.in);
        clienteDAO = new ClienteDAO();
        mascotaDAO = new MascotaDAO();
        ventaDAO = new VentaDAO();
    }
    public void menu() {
        int opcion;
        do {
            System.out.println("**CRUD Tienda de Mascotas**");
            System.out.println("1. Clientes");
            System.out.println("2. Mascotas");
            System.out.println("3. Ventas");
            System.out.println("4. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuMascota();
                    break;
                case 3:
                    menuVenta();
                    break;
            }
        } while (opcion != 4);
    }
    private void menuCliente() {
        int opcion;
        do {
            System.out.println("**Clientes**");
            System.out.println("1. Crear cliente");
            System.out.println("2. Leer clientes");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearCliente();
                    break;
                case 2:
                    leerClientes();
                    break;
                case 3:
                    actualizarCliente();
                    break;
                case 4:
                    eliminarCliente();
                    break;
            }
        } while (opcion != 5);
    }
    private void crearCliente() {
        System.out.println("**Crear Cliente**");
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Telefono: ");
        String telefono = scanner.next();
        System.out.print("Correo: ");
        String correo = scanner.next();

        Cliente cliente = new Cliente(0, nombre, telefono, correo);
        clienteDAO.create(cliente);
        System.out.println("Cliente creado exitosamente!");
    }
    private void leerClientes() {
        System.out.println("**Leer Clientes**");
        List<Cliente> clientes = clienteDAO.readAll();
        for (Cliente cliente : clientes) {
            System.out.println("Cliente: " + cliente.getIdCliente());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Telefono: " + cliente.getTelefono());
            System.out.println("Correo: " + cliente.getCorreo());
            System.out.println();
        }
    }
    public void actualizarCliente() {
        System.out.println("**Actualizar Cliente**");
        System.out.print("ID del cliente: ");
        int idCliente = scanner.nextInt();
        Cliente cliente = clienteDAO.read(idCliente);
        if (cliente != null) {
            System.out.print("Nombre (" + cliente.getNombre() + "): ");
            String nombre = scanner.next();
            System.out.print("Telefono (" + cliente.getTelefono() + "): ");
            String telefono = scanner.next();
            System.out.print("Correo (" + cliente.getCorreo() + "): ");
            String correo = scanner.next();

            cliente.setNombre(nombre);
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);
            clienteDAO.update(cliente);
            System.out.println("Cliente actualizado exitosamente!");
        } else {
            System.out.println("Cliente no encontrado!");
        }
    }

    private void eliminarCliente() {
        System.out.println("**Eliminar Cliente**");
        System.out.print("ID del cliente: ");
        int idCliente = scanner.nextInt();
        clienteDAO.delete(idCliente);
        System.out.println("Cliente eliminado exitosamente!");
    }

    private void menuMascota() {
        int opcion;
        do {
            System.out.println("**Mascotas**");
            System.out.println("1. Crear mascota");
            System.out.println("2. Leer mascotas");
            System.out.println("3. Actualizar mascota");
            System.out.println("4. Eliminar mascota");
            System.out.println("5. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearMascota();
                    break;
                case 2:
                    leerMascotas();
                    break;
                case 3:
                    actualizarMascota();
                    break;
                case 4:
                    eliminarMascota();
                    break;
            }
        } while (opcion != 5);
    }
    private void crearMascota() {
        System.out.println("**Crear Mascota**");
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Especie: ");
        String especie = scanner.next();
        System.out.print("Raza: ");
        String raza = scanner.next();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();

        Mascota mascota = new Mascota(0, nombre, especie, raza, edad, precio);
        mascotaDAO.create(mascota);
        System.out.println("Mascota creada exitosamente!");
    }
    private void leerMascotas() {
        System.out.println("**Leer Mascotas**");
        List<Mascota> mascotas = mascotaDAO.readAll();
        for (Mascota mascota : mascotas) {
            System.out.println("Mascota: " + mascota.getIdMascota());
            System.out.println("Nombre: " + mascota.getNombre());
            System.out.println("Especie: " + mascota.getEspecie());
            System.out.println("Raza: " + mascota.getRaza());
            System.out.println("Edad: " + mascota.getEdad());
            System.out.println("Precio: " + mascota.getPrecio());
            System.out.println();
        }
    }
    public void actualizarMascota() {
        System.out.println("**Actualizar Mascota**");
        System.out.print("ID de la mascota: ");
        int idMascota = scanner.nextInt();
        Mascota mascota = mascotaDAO.read(idMascota);
        if (mascota != null) {
            System.out.print("Nombre (" + mascota.getNombre() + "): ");
            String nombre = scanner.next();
            System.out.print("Especie (" + mascota.getEspecie() + "): ");
            String especie = scanner.next();
            System.out.print("Raza (" + mascota.getRaza() + "): ");
            String raza = scanner.next();
            System.out.print("Edad (" + mascota.getEdad() + "): ");
            int edad = scanner.nextInt();
            System.out.print("Precio (" + mascota.getPrecio() + "): ");
            double precio = scanner.nextDouble();

            mascota.setNombre(nombre);
            mascota.setEspecie(especie);
            mascota.setRaza(raza);
            mascota.setEdad(edad);
            mascota.setPrecio(precio);
            mascotaDAO.update(mascota);
            System.out.println("Mascota actualizada exitosamente!");
        } else {
            System.out.println("Mascota no encontrada!");
        }
    }
    private void eliminarMascota() {
        System.out.println("**Eliminar Mascota**");
        System.out.print("ID de la mascota: ");
        int idMascota = scanner.nextInt();
        mascotaDAO.delete(idMascota);
        System.out.println("Mascota eliminada exitosamente!");
    }

    private void menuVenta() {
        int opcion;
        do {
            System.out.println("**Ventas**");
            System.out.println("1. Crear venta");
            System.out.println("2. Leer ventas");
            System.out.println("3. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearVenta();
                    break;
                case 2:
                    leerVentas();
                    break;
            }
        } while (opcion != 3);
    }

    private void crearVenta() {
        System.out.println("**Crear Venta**");
        System.out.print("ID de la mascota: ");
        int idMascota = scanner.nextInt();
        System.out.print("ID del cliente: ");
        int idCliente = scanner.nextInt();

        Mascota mascota = mascotaDAO.read(idMascota);
        Cliente cliente = clienteDAO.read(idCliente);

        if (mascota != null && cliente != null) {
            Venta venta = new Venta(0, mascota, cliente, new Date(), mascota.getPrecio());
            ventaDAO.create(venta);
            System.out.println("Venta realizada exitosamente!");
        } else {
            System.out.println("Mascota o cliente no encontrado!");
        }
    }
    private void leerVentas() {
        System.out.println("**Leer Ventas**");
        List<Venta> ventas = ventaDAO.readAll();
        for (Venta venta : ventas) {
            System.out.println("Venta: " + venta.getIdVenta());
            System.out.println("Mascota: " + venta.getMascota().getNombre());
            System.out.println("Cliente: " + venta.getCliente().getNombre());
            System.out.println("Fecha: " + venta.getFechaVenta());
            System.out.println("Total: " + venta.getTotal());
            System.out.println();
        }
    }

}
