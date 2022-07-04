package fr.sg.mower.kata.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MowerError {

    MOWER_EMPTY_FILE("Empty File"),
    MOWER_WRONG_FILE_PATH("Wrong File Path"),
    MOWER_WRONG_MAX_LENGTH_OR_FORMAT("Wrong Max Length Or Format"),
    MOWER_WRONG_ORIENTATION("Wrong Orientation "),
    MOWER_WRONG_COMMAND("Wrong Command "),
    MOWER_NOT_FOUND_ORIENTATION("Not Found Orientation ");

    private String message;
}
