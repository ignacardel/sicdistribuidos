/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 * Clase que sirve de puente entre la interfaz y la lectura de la lista
 * de archivos xml para poder cargar la lista de archivos
 * @author ignaciocardenas
 */
public class Archivo {

    private String nombre;
    private String estado;
    private Long cantidad;
    private Long total;

    public Archivo() {
    }

    public Archivo(String nombre, String estado, Long cantidad, Long total) {
        this.nombre = nombre;
        this.estado = estado;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
