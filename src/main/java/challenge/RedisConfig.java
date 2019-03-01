package challenge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public <T> RedisTemplate<String, T> redisTemplateBuilder(RedisConnectionFactory connectionFactory) {
        final RedisTemplate<String, T> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(NeighborhoodRedis.class));
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }

}