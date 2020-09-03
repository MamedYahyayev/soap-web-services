package az.maqa.spring.repository;

import az.maqa.spring.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie , Long> {

    Movie findByTitle(String title);
}
