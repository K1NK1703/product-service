package ru.romanov.marketplace.productservice.persistence;

import org.springframework.stereotype.Component;
import org.springframework.dao.DataIntegrityViolationException;

@Component
public class PersistenceExceptionMapper {

    public PersistenceException map(Throwable throwable) {
        if (throwable instanceof DataIntegrityViolationException) {
            if (isForeignKeyViolation(throwable.getMessage())) {
                return new PersistenceException.ForeignKeyViolated(throwable.getMessage(), throwable);
            }
        }
        return new PersistenceException.NotExpected(throwable.getMessage(), throwable);
    }

    private boolean isForeignKeyViolation(String message) {
        return message.contains("ERROR: insert or update on table") &&
                message.contains("violates foreign key constraint");
    }
}
