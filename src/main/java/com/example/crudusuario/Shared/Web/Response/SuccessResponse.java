package com.example.crudusuario.Shared.Web.Response;

import org.springframework.http.HttpStatus;

public record SuccessResponse<T>(
        boolean success,
        int status,
        T data
) {

    public static <T> SuccessResponse<T> ok(T data){
        return new SuccessResponse<>(
                true,
                HttpStatus.OK.value(),
                data
        );
    }

    public static <T> SuccessResponse<T> created(T data){
        return new SuccessResponse<>(
                true,
                HttpStatus.CREATED.value(),
                data
        );
    }
}
