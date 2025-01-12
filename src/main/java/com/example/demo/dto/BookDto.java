package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder

public record BookDto(
    Long id,
    String title,
    String author,
    double price){

}
