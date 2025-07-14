package com.verdiq.controller;


import com.verdiq.controller.request.MovieRequest;
import com.verdiq.controller.response.MovieResponse;
import com.verdiq.entity.Movie;
import com.verdiq.mapper.MovieMapper;
import com.verdiq.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/verdiq/movie")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovie() {
        return ResponseEntity.ok(movieService.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @PostMapping
    public ResponseEntity<MovieResponse> saveMovie(@RequestBody MovieRequest request) {
        Movie newMovie = MovieMapper.toMovieRequest(request);
        Movie savedMovie = movieService.saveMovie(newMovie);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MovieMapper
                        .toMovieResponse(savedMovie)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getByMovieId(@PathVariable Long id) {
        return movieService.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity
                        .notFound()
                        .build()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByMovieId(@PathVariable Long id) {
        Optional<Movie> optMovie = movieService.findById(id);
        if (optMovie.isPresent()) {
            movieService.deleteById(id);
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.notFound()
                .build();
    }


    @PutMapping({"/{id}"})
    public ResponseEntity<MovieResponse> getByMovieId(@PathVariable Long id, @RequestBody MovieRequest request) {
        return movieService.update(id, MovieMapper.toMovieRequest(request))
                .map(movie -> ResponseEntity
                        .ok(MovieMapper
                        .toMovieResponse(movie))
                )
                .orElse(ResponseEntity
                        .notFound()
                        .build()
                );
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategories(@RequestParam Long category) {
        return ResponseEntity.ok(movieService
                .findByCategories(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList()
        );
    }

}
