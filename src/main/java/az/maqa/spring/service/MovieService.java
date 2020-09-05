package az.maqa.spring.service;

import az.maqa.spring.entity.Movie;
import az.maqa.spring.gs_ws.GetAllMoviesResponse;
import az.maqa.spring.gs_ws.GetMovieByIdResponse;

import java.util.List;

public interface MovieService {

    GetMovieByIdResponse getEntityById(long id);

    Movie getEntityByTitle(String title);

    GetAllMoviesResponse getAllEntities();

    Movie addEntity(Movie entity);

    boolean updateEntity(Movie entity);

    boolean deleteEntityById(long id);

}
