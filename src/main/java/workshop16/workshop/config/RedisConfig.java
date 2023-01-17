package workshop16.workshop.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import workshop16.workshop.model.Mastermind;

@Configuration
public class RedisConfig {

    // value redis host from appln.properties
    @Value("${spring.redis.host}")
    private String redisHost;

    // value redis port from appln.properties
    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort;

    @Value("${spring.redis.username}")
    private String redisUsername;

    @Value("${spring.redis.password}")
    private String redisPassword;


    // define the return redis template bean as single Object
    // throughout the runtime.
    // Return the RedisTemplate
    @Bean
    @Scope("singleton")
    public RedisTemplate<String, Mastermind> redisTemplate(){
        final RedisStandaloneConfiguration config 
                = new RedisStandaloneConfiguration();

        config.setHostName(redisHost);
        config.setPort(redisPort.get());
        
        if(!redisUsername.isEmpty() && !redisPassword.isEmpty()){
            config.setUsername(redisUsername);
            config.setPassword(redisPassword);
        }
        config.setDatabase(0);

        final JedisClientConfiguration jedisClient = JedisClientConfiguration
                                        .builder()
                                        .build();
        final JedisConnectionFactory jedisFac= 
                            new JedisConnectionFactory(config, jedisClient);
        jedisFac.afterPropertiesSet();
        RedisTemplate<String, Mastermind> redisTemplate = 
                    new RedisTemplate<String, Mastermind>();
        // associate with the redis connection
        redisTemplate.setConnectionFactory(jedisFac);
        Jackson2JsonRedisSerializer jackson2Serializer = new Jackson2JsonRedisSerializer(Mastermind.class);
        
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // set the map key/value serialization type to String
        redisTemplate.setHashKeySerializer(redisTemplate.getKeySerializer());
        redisTemplate.setValueSerializer(jackson2Serializer);
        redisTemplate.setHashValueSerializer(jackson2Serializer);
        
        return redisTemplate;
    }
}