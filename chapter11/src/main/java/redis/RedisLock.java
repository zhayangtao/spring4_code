package redis;

import java.util.Random;

/**
 * Created by shliangyan on 2017/9/29.
 */
public class RedisLock {
    public static final long MILLI_NANO_TIME = 1000 * 1000L;
    public static final String LOCKED = "true";
    public static final Random RANDOM = new Random();
    private String key;

    private RedisClient redisClient;
    private boolean lock = true;

    public RedisLock(String purpose, String key) {
        this.key = purpose + "";
    }

    public boolean lock(long timeout, int expire) {

        return false;
    }

}
