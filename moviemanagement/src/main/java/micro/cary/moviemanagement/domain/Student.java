package micro.cary.moviemanagement.domain;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Student")
public class Student implements Serializable {
    private String id;
    private String name;
    private Integer age;    
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}