package model.exception;

public class InvalidMaskException extends IllegalArgumentException {
    public InvalidMaskException(String s) {
        super(s);
    }
}
