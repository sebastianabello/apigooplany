package org.example.negocio;

import java.io.FileWriter;
import java.io.IOException;

public class GuardarDatosTxt {
    private final String archivoDatos;

    public GuardarDatosTxt() {
        this.archivoDatos = "datos.txt"; // Puedes configurar el nombre del archivo en el constructor o leerlo de alguna propiedad
    }

    public void guardarDatos(String nombre) {
        try (FileWriter writer = new FileWriter(archivoDatos, true)) { // Usa append=true para agregar al final del archivo
            writer.write("Nombre: " + nombre + "\n");
            writer.write("Apellido: " +  "\n");
            writer.write("Edad: " + "\n");
            writer.write("\n"); // Agrega una línea en blanco para separar registros
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
