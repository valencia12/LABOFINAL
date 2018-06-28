/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Filtro {
    private int id;
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private String tipo;
    private boolean disponibilidad;

    public Filtro() {
    }

    public Filtro(int id, String codigo, String nombre, double precio, int cantidad, String tipo, boolean disponiblidad) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.disponibilidad = disponiblidad;
    }

    public Filtro(String codigo, String nombre, double precio, int cantidad, String tipo, boolean disponibilidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
    }

    public Filtro(String nombre, double precio, int cantidad, String tipo, boolean disponibilidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
    }
    public int getId() {
        return id;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo=codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponiblidad) {
        this.disponibilidad = disponiblidad;
    }
}
