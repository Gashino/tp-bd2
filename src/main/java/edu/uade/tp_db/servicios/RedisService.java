package edu.uade.tp_db.servicios;


import edu.uade.tp_db.entidades.Carrito;
import edu.uade.tp_db.entidades.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void redisSaveCarrito(Carrito carrito) {
        String key= carrito.getId();
        redisTemplate.opsForList().leftPush(key,carrito.items);
    }

    public void redisDesapilarCarrito(Carrito carrito) {
        String key = carrito.getId();
        Object items = redisTemplate.opsForList().leftPop(key);
        carrito.setItems((List<Item>) items);
    }
}

