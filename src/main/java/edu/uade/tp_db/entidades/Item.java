package edu.uade.tp_db.entidades;

public class Item {

    private String idProducto;

    private int cantidad;

    public Item(String idProducto, int cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Item{" +
                "idProducto='" + idProducto + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
