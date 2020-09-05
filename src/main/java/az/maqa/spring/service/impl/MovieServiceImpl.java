package az.maqa.spring.service.impl;

import az.maqa.spring.entity.Movie;
import az.maqa.spring.exception.ExceptionConstants;
import az.maqa.spring.gs_ws.GetAllMoviesResponse;
import az.maqa.spring.gs_ws.GetMovieByIdResponse;
import az.maqa.spring.gs_ws.MovieType;
import az.maqa.spring.gs_ws.ServiceStatus;
import az.maqa.spring.repository.MovieRepository;
import az.maqa.spring.service.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public GetMovieByIdResponse getEntityById(long id) {
        GetMovieByIdResponse response = new GetMovieByIdResponse();
        ServiceStatus status = new ServiceStatus();
        Movie movie = movieRepository.getMovieByMovieId(id);

        if (movie == null) {
            status.setStatusCode(ExceptionConstants.MOVIE_NOT_FOUND);
            status.setMessage("Movie Not Found");
            response.setServiceStatus(status);
            return response;
        }

        MovieType movieType = new MovieType();
        BeanUtils.copyProperties(movie, movieType);
        status.setStatusCode(1);
        status.setMessage("Success");
        response.setServiceStatus(status);
        response.setMovieType(movieType);
        return response;
    }

    @Override
    public Movie getEntityByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public GetAllMoviesResponse getAllEntities() {
        GetAllMoviesResponse response = new GetAllMoviesResponse();
        MovieType movieType = new MovieType();
        List<MovieType> movieTypeList = new ArrayList<>();
        List<Movie> movies = movieRepository.findAll();
        ServiceStatus serviceStatus = new ServiceStatus();

        if (movies == null || movies.isEmpty()) {
            serviceStatus.setStatusCode(ExceptionConstants.MOVIE_NOT_FOUND);
            serviceStatus.setMessage("Movies not found");
            response.setServiceStatus(serviceStatus);
            return response;
        }

        for (Movie movie : movies) {
            BeanUtils.copyProperties(movie, movieType);
            movieTypeList.add(movieType);
        }

        response.getMovieType().addAll(movieTypeList);
        serviceStatus.setStatusCode(1);
        serviceStatus.setMessage("Success");

        return response;
    }

    @Override
    public Movie addEntity(Movie movie) {
        try {
            return movieRepository.save(movie);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateEntity(Movie movie) {
        try {
            movieRepository.save(movie);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEntityById(long id) {
        try {
            movieRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
