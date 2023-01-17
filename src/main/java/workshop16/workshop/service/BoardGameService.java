package workshop16.workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import workshop16.workshop.model.Mastermind;

@Service
public class BoardGameService {
  
  @Autowired
  RedisTemplate<String, Mastermind> redisTemplate;

  public int saveGame(final Mastermind ms){
    // Set Mastermind id as key and Mastermind object as value IN REDIS
    redisTemplate.opsForValue().set(ms.getId(), ms);
    Mastermind result = redisTemplate.opsForValue().get(ms.getId());
    if(result == null){
      return 0;
    }
    return 1;
  }

  public Mastermind findById(final String msId){
    return redisTemplate.opsForValue().get(msId);
  }
}
