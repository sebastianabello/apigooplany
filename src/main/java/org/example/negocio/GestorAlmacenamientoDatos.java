package org.example.negocio;

public class GestorAlmacenamientoDatos {
    private final GuardarDatosBD guardarDatosBD;
    private final GuardarDatosTxt guardarDatosTxt;

    public GestorAlmacenamientoDatos() {
        this.guardarDatosBD = new GuardarDatosBD();
        this.guardarDatosTxt = new GuardarDatosTxt();
    }
    public void guardarDatos(String nombre, TipoAlmacenamiento tipoAlmacenamiento) {
        switch (tipoAlmacenamiento) {
            case BASE_DATOS:
                guardarDatosBD.guardarDatos(nombre);
                break;
            case ARCHIVO_TXT:
                guardarDatosTxt.guardarDatos(nombre);
                break;
        }
    }

    public enum TipoAlmacenamiento {
        BASE_DATOS,
        ARCHIVO_TXT
    }
}
