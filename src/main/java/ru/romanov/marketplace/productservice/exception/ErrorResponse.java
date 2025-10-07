package ru.romanov.marketplace.productservice.exception;

public record ErrorResponse(

        String message,

        String timestamp,

        String path,

        String traceId

) {}
