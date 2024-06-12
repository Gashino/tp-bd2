package edu.uade.tp_db.entidades;

import java.util.List;

public class Factura {
    List<Item> items;
    int total;
    EstadoFactura estado;
    MetodoDePago metodoDePago;
    int restante;


    public Factura(List<Item> items, int total, MetodoDePago metodoDePago) {
        this.items = items;
        this.total = total;
        this.metodoDePago = metodoDePago;
        this.estado = EstadoFactura.PENDIENTE;
        this.restante = total;
    }

    public int getRestante() {
        return restante;
    }

    public void setRestante(int restante) {
        this.restante = restante;
    }

    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(MetodoDePago metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public void setEstado(EstadoFactura estado) {
        this.estado = estado;
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

