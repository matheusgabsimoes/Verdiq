package com.verdiq.controller.request;


import lombok.Builder;

@Builder
public record UserRequest(String name,
                          String email,
                          String password) {
}
