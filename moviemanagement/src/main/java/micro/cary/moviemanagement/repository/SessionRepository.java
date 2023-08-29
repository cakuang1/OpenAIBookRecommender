package micro.cary.moviemanagement.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import micro.cary.moviemanagement.domain.Sessions;

@Repository
public interface SessionRepository extends CrudRepository<Sessions, String> {


    
}