# Crud Tienda de mascotas
## Base de datos:
- Crear la base de datos  
CREATE DATABASE tienda_mascotas;

- Seleccionar la base de datos  
USE tienda_mascotas;

- Crear la tabla Mascotas  
CREATE TABLE Mascotas (
    id_mascota INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    especie VARCHAR(255) NOT NULL,
    raza VARCHAR(255) NOT NULL,
    edad INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL
);

- Crear la tabla Clientes  
CREATE TABLE Clientes (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    telefono VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL
);

- Crear la tabla Ventas  
CREATE TABLE Ventas (
    id_venta INT PRIMARY KEY AUTO_INCREMENT,
    id_mascota INT NOT NULL,
    id_cliente INT NOT NULL,
    fecha_venta DATE NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_mascota) REFERENCES Mascotas(id_mascota),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente)
);

- Insertar algunos datos de ejemplo  
  - INSERT INTO Mascotas (nombre, especie, raza, edad, precio) VALUES
    ('Toby', 'Perro', 'Labrador Retriever', 2, 500000),
    ('Luna', 'Gato', 'Siamés', 1, 300000),
    ('Pepito', 'Perro', 'Beagle', 3, 400000);

  - INSERT INTO Clientes (nombre, telefono, correo) VALUES
    ('Juan Pérez', '3123456789', 'juan.perez@correo.com'),
    ('María López', '3001234567', 'maria.lopez@correo.com');

  - INSERT INTO Ventas (id_mascota, id_cliente, fecha_venta, total) VALUES
    (1, 1, '2023-11-14', 500000),
    (2, 2, '2023-11-15', 300000);
