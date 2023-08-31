package micro.cary.moviemanagement.repository;

import org.springframework.data.repository.CrudRepository;

import micro.cary.moviemanagement.domain.Student;

public interface StudentRepository extends CrudRepository<Student, String> {
}