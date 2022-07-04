package fr.sg.mower.kata.domain.enums;

import fr.sg.mower.kata.exceptions.MowerError;
import fr.sg.mower.kata.exceptions.MowerException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author Wassim Gaied
 */
@Getter
@AllArgsConstructor
public enum Orientation {

    N("N", 0, 1, 90), E("E", 1, 0, 0), S("S", 0, -1, 270), W("W", -1, 0, 180);

    private String label;
    Integer x;
    Integer y;
    Integer angle;


    public static Orientation getOrientationByLabel(String label) {
        Orientation orientation;

        switch (label) {
            case "N":
                orientation = Orientation.N;
                break;
            case "E":
                orientation = Orientation.E;
                break;
            case "S":
                orientation = Orientation.S;
                break;
            case "W":
                orientation = Orientation.W;
                break;
            default:
                throw new MowerException(MowerError.MOWER_WRONG_ORIENTATION);
        }

        return orientation;
    }


    public static Orientation getNextOrientation(Integer x, Integer y) {

        return Arrays.stream(Orientation.values())
                .filter(orientation -> orientation.getX().equals(x) && orientation.getY().equals(y))
                .findFirst()
                .orElseThrow(() -> new MowerException(MowerError.MOWER_NOT_FOUND_ORIENTATION));
    }

}
