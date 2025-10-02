package ru.romanov.marketplace.productservice.rest.advice;

public record ErrorResponse(

        String message,

        String timestamp,

        String path

) {}
