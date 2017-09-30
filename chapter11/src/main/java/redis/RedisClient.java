package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by shliangyan on 2017/9/29.
 */
public class RedisClient {
    public JedisPool jedisPool;

    public RedisClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public RedisClient() {

    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 根据Key获取对应的Value
     * @param key
     * @return
     */
    public Object getByKey(String key) {
        Jedis client = jedisPool.getResource();
        Object o = null;
        try {
            o = client.get(key);
        } finally {
            jedisPool.close();
        }
        return o;
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean isKeyExist(String key) {
        Jedis client = jedisPool.getResource();
        boolean o = false;
        try {
            o = client.exists(key);
        } finally {
            jedisPool.close();
        }
        return o;
    }

    public boolean set(String key, String value) {
        Jedis client = jedisPool.getResource();
        String isSuccess = "";
        try {
            isSuccess = client.set(key, value);
            if ("OK".equalsIgnoreCase(isSuccess)) {
                return true;
            }
            return false;
        } finally {
            jedisPool.close();
        }
    }

}
