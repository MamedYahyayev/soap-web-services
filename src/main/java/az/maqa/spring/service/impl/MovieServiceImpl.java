package az.maqa.spring.service.impl;

import az.maqa.spring.entity.Movie;
import az.maqa.spring.repository.MovieRepository;
import az.maqa.spring.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public Movie getEntityById(long id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public Movie getEntityByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> getAllEntities() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movie -> movies.add(movie));
        return movies;
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
