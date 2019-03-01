package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NeighborhoodRedisRepository {

    @Autowired
    private RedisTemplate<String, NeighborhoodRedis> redisTemplate;

    public NeighborhoodRedis save(String key, NeighborhoodRedis neighborhoodRedis) {
        redisTemplate.opsForValue().set("neighborhood:" + key, neighborhoodRedis);
        return neighborhoodRedis;
    }

    public Optional<NeighborhoodRedis> findById(String id) {
        return Optional.ofNullable(redisTemplate.opsForValue().get("neighborhood:" + id));
    }

}
