package com.verdiq.controller.request;

import lombok.Builder;

@Builder
public record CategoryRequest(String name) {
}
