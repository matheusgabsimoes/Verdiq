package com.verdiq.repository;

import com.verdiq.entity.Category;
import com.verdiq.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMoviesByCategories(List<Category> categories);
}
