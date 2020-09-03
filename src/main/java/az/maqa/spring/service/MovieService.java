package az.maqa.spring.service;

import az.maqa.spring.entity.Movie;

import java.util.List;

public interface MovieService {

    Movie getEntityById(long id);

    Movie getEntityByTitle(String title);

    List<Movie> getAllEntities();

    Movie addEntity(Movie entity);

    boolean updateEntity(Movie entity);

    boolean deleteEntityById(long id);

}
