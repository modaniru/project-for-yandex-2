package com.example.projectforyandexx2.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponseException {
    private String message;
    private int code;
}
