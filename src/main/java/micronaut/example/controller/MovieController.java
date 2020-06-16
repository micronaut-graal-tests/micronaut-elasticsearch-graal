package micronaut.example.controller;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.annotation.Status;
import micronaut.example.service.MovieMapper;
import micronaut.example.service.Movie;
import micronaut.example.service.MovieService;

import javax.validation.Valid;

@Controller("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @Post
    @Status(HttpStatus.CREATED)
    public String createMovie(@Valid @Body CreateMovieRequest createMovieRequest) {
        Movie movie = movieMapper.toMovie(createMovieRequest);
        return movieService.saveMovie(movie);
    }

    @Get
    public SearchMovieResponse searchMovies(@QueryValue("title") String title) {
        return movieService.searchMovies(title);
    }

}
