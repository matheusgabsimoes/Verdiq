package com.verdiq.mapper;


import com.verdiq.controller.request.MovieRequest;
import com.verdiq.controller.response.CategoryResponse;
import com.verdiq.controller.response.MovieResponse;
import com.verdiq.controller.response.StreamingResponse;
import com.verdiq.entity.Category;
import com.verdiq.entity.Movie;
import com.verdiq.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toMovieRequest(MovieRequest movieRequest) {

        List<Category> categories = movieRequest.categories()
                .stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = movieRequest.streamings()
                .stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie
                .builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .rating(movieRequest.rating())
                .releaseDate(movieRequest.releaseDate())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie) {

        List<CategoryResponse> categories = movie.getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return MovieResponse
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
