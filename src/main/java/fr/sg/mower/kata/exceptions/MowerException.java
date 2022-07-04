package fr.sg.mower.kata.exceptions;

/**
 * @author Wassim Gaied
 */
public class MowerException extends RuntimeException {
    public MowerException(MowerError mowerError) {
        super(mowerError.getMessage());
    }
}
