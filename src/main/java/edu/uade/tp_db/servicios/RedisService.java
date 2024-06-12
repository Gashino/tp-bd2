package edu.uade.tp_db.servicios;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uade.tp_db.entidades.Carrito;
import edu.uade.tp_db.entidades.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    ObjectMapper mapper = new ObjectMapper();



    public void redisSaveCarrito(Carrito carrito) {
        String key= carrito.getId();
        redisTemplate.opsForList().leftPush(key,carrito.items);
    }

    public void redisDesapilarCarrito(Carrito carrito) {
        String key = carrito.getId();
        List<Map<String, Object>> itemList = (List<Map<String, Object>>) redisTemplate.opsForList().leftPop(key);
        List<Item> itemsList = new ArrayList<>();
        if (itemList == null) {
            carrito.setItems(itemsList); 
        }
        else {
            for (Map<String, Object> itemMap : itemList) {
                Item item = new Item((String) itemMap.get("idProducto"), (Integer) itemMap.get("cantidad"));
                itemsList.add(item);
            }
            carrito.setItems(itemsList);
        }
    }
}

