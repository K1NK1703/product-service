package ru.romanov.marketplace.productservice.service;

import java.io.Serial;

public sealed class ServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3777225522073808176L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    final public static class CreationException extends ServiceException {

        @Serial
        private static final long serialVersionUID = -2190714934560693702L;

        public CreationException(String message) {
            super(message);
        }
    }

    final public static class NotFoundException extends ServiceException {

        @Serial
        private static final long serialVersionUID = 1773508033010380639L;

        public NotFoundException(String message) {
            super(message);
        }
    }

    final public static class ConversionException extends ServiceException {

        @Serial
        private static final long serialVersionUID = -807684479440799442L;

        public ConversionException(String message) {
            super(message);
        }
    }

    final public static class UpdateException extends ServiceException {

        @Serial
        private static final long serialVersionUID = -1720875583181640429L;

        public UpdateException(String message) {
            super(message);
        }
    }

    final public static class NotExistException extends ServiceException {

        @Serial
        private static final long serialVersionUID = 8857318283531780735L;

        public NotExistException(String message) {
            super(message);
        }
    }
}
