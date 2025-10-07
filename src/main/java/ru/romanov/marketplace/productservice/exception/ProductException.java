package ru.romanov.marketplace.productservice.exception;

import java.io.Serial;

public sealed class ProductException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3777225522073808176L;

    public ProductException(String message) {
        super(message);
    }

    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }

    final public static class CreationException extends ProductException {

        @Serial
        private static final long serialVersionUID = -2190714934560693702L;

        public CreationException(String message) {
            super(message);
        }
    }

    final public static class NotFoundException extends ProductException {

        @Serial
        private static final long serialVersionUID = 1773508033010380639L;

        public NotFoundException(String message) {
            super(message);
        }
    }

    final public static class ConversionException extends ProductException {

        @Serial
        private static final long serialVersionUID = -807684479440799442L;

        public ConversionException(String message) {
            super(message);
        }
    }

    final public static class UpdateException extends ProductException {

        @Serial
        private static final long serialVersionUID = -1720875583181640429L;

        public UpdateException(String message) {
            super(message);
        }
    }

    final public static class DeletedException extends ProductException {

        @Serial
        private static final long serialVersionUID = -5812904561886343031L;

        public DeletedException(String message) {
            super(message);
        }
    }
}
