package com.ninebudget.vendor;

import com.ninebudget.model.CacheVendor;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

public class Redis implements CacheVendor {
    private Jedis jedis = new Jedis();

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public void set(String key, String value) {
        jedis.set(key, value);
    }
}
