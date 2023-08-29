package micro.cary.moviemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import micro.cary.moviemanagement.domain.Movie;




@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private RedisTemplate<String,Movie> redisTemplate;    

    @PostMapping("/add")
    public String addMovie(@RequestBody Movie movie ) {
        redisTemplate.opsForHash().put("movies", movie.getId(), movie);
        return "Movie added to Redis";
    }


    @DeleteMapping("delete/{id}")
    public String deleteMovie(@PathVariable String id) {
        Long deleted = redisTemplate.opsForHash().delete("movies", id);
        if (deleted > 0) {
            return "Movie deleted from Redis";
        } else {
            return "Movie not found in Redis";
        }
    }
}