package edu.uade.tp_db.entidades;

import java.util.List;

public class Factura {
    List<Item> items;
    int total;
    EstadoFactura estado;
    MetodoDePago metodoDePago;


    public Factura(List<Item> items, int total, MetodoDePago metodoDePago) {
        this.items = items;
        this.total = total;
        this.metodoDePago = metodoDePago;
        this.estado = EstadoFactura.PENDIENTE;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}

