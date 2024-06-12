package edu.uade.tp_db.servicios;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uade.tp_db.entidades.Carrito;
import edu.uade.tp_db.entidades.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    ObjectMapper mapper = new ObjectMapper();



    public void redisSaveCarrito(Carrito carrito) {
        String key= carrito.getId();
        redisTemplate.opsForList().leftPush(key,carrito.items);
        redisTemplate.expire(key, 1, TimeUnit.HOURS);
    }

    public void redisDesapilarCarrito(Carrito carrito) {
        String key = carrito.getId();
        List<Map<String, Object>> itemList = (List<Map<String, Object>>) redisTemplate.opsForList().leftPop(key);
        List<Item> itemsList = new ArrayList<>();
        if (itemList == null) {
            carrito.setItems(itemsList);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Se vacio el carrito por completo");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        else {
            for (Map<String, Object> itemMap : itemList) {
                Item item = new Item((String) itemMap.get("idProducto"), (Integer) itemMap.get("cantidad"));
                itemsList.add(item);
            }
            carrito.setItems(itemsList);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Carrito retrocedido");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }
}

