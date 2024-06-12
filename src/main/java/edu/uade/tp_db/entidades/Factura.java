package edu.uade.tp_db.entidades;

import java.security.SecureRandom;
import java.util.List;

public class Factura {

    private String idFactura;
    private List<Item> items;
    private int total;
    private EstadoFactura estado;
    private MetodoDePago metodoDePago;
    private int restante;


    public Factura(List<Item> items, int total, MetodoDePago metodoDePago) {
        this.idFactura = idGenerador();
        this.items = items;
        this.total = total;
        this.metodoDePago = metodoDePago;
        this.estado = EstadoFactura.PENDIENTE;
        this.restante = total;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
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

    public void registrarPago(int monto){
        this.restante -= monto;
        if(this.restante <= 0){
            this.restante=0;
            this.estado = EstadoFactura.PAGADA;
            System.out.println("--------------------FELCIDADES. SU FACTURA QUEDO PAGADA COMPLETAMENTE--------------------");
            System.out.println("");
        }
        else{
            System.out.println("--------------------PAGO REGISTRADO---------------------------------------");
            System.out.println("--------------------AUN RESTA PAGAR: "+this.restante+"--------------------");
            System.out.println("");
        }
    }

    public String idGenerador(){
        SecureRandom random = new SecureRandom();
        String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

            StringBuilder sb = new StringBuilder(10);
            for (int i = 0; i < 10; i++) {
                int index = random.nextInt(CHAR_SET.length());
                sb.append(CHAR_SET.charAt(index));
            }
            return sb.toString();

    }

}

