package com.projecthub.dto;

import lombok.Data;

@Data
public class AuthResponseDto {

    private Long id;
    private String token;

    public AuthResponseDto(Long id, String token) {
        this.id = id;
        this.token = token;
    }
}
