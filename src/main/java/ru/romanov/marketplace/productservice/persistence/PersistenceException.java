package ru.romanov.marketplace.productservice.persistence;

import java.io.Serial;

public sealed class PersistenceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4376781862444861105L;

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    final public static class NotFound extends PersistenceException {

        @Serial
        private static final long serialVersionUID = -8996222269594287147L;

        public NotFound(String message) {
            super(message);
        }
    }

    final public static class ForeignKeyViolated extends PersistenceException {

        @Serial
        private static final long serialVersionUID = -2892260602660262940L;

        public ForeignKeyViolated(String message, Throwable cause) {
            super(message, cause);
        }
    }

    final public static class NotExpected extends PersistenceException {

        @Serial
        private static final long serialVersionUID = 4305468857747456479L;

        public NotExpected(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
