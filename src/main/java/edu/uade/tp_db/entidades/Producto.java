package edu.uade.tp_db.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
public class Producto {

    @Id
    private String id;

    private int idNormal;

    private String nombre;

    private int precio;

    public Producto(int idNormal,String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.idNormal = idNormal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdNormal() {
        return idNormal;
    }

    public void setIdNormal(int idNormal) {
        this.idNormal = idNormal;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
