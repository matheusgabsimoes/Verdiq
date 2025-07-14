package com.verdiq.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.verdiq.entity.Streaming;
import jdk.jfr.Category;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(Long id,
                            String title,
                            String description,
                            double rating,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            LocalDate releaseDate,
                            List<CategoryResponse> categories,
                            List<StreamingResponse> streamings) {
}
