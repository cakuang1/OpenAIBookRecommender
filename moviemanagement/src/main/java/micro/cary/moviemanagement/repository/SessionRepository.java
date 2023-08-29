package micro.cary.moviemanagement.repository;
import org.springframework.data.repository.CrudRepository;
import micro.cary.moviemanagement.domain.Sessions;


public interface SessionRepository extends CrudRepository<Sessions, String> {

}