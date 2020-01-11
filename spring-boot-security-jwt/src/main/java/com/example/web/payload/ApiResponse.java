package com.example.web.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    private int statusCode;
    private T data;
    private String message;
}
