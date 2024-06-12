package edu.uade.tp_db.entidades;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "carritos")
public class Carrito {

    @Id
    public String id;

    public String usuario;

    public List<Item> items;


    public Carrito(String usuario) {
        this.usuario = usuario;
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addItem(Item item) {
        boolean itemExiste = false;

        for (Item i : items) {
            if (i.getIdProducto().equals(item.getIdProducto())) {
                i.setCantidad(i.getCantidad() + item.getCantidad());
                itemExiste = true;
                break;
            }
        }

        if(!itemExiste){
            this.items.add(item);
        }
    }

    public void removeItem(Item item) {
        boolean itemExiste = false;

        for (Item i : items) {
            if (i.getIdProducto().equals(item.getIdProducto())) {
                int cantidad = i.getCantidad() - item.getCantidad();
                itemExiste = true;
                if(cantidad > 0){
                    i.setCantidad(cantidad);
                }
                else {
                    items.remove(i);
                }
                System.out.println("Item eliminado o reducido del carrito");
                System.out.println("----------------------------------------------");
                break;
            }
        }

        if(!itemExiste){
            System.out.println("El item no existe en el carrito");
            System.out.println("----------------------------------------------");
        }
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "id='" + id + '\'' +
                ", usuario='" + usuario + '\'' +
                ", items=" + items +
                '}';
    }
}




