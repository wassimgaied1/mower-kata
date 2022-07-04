package fr.sg.mower.kata.domain.enums;


import fr.sg.mower.kata.exceptions.MowerError;
import fr.sg.mower.kata.exceptions.MowerException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wassim Gaied
 */
@Getter
@AllArgsConstructor
public enum Command {

    L('G', 90), R('D', -90), A('A', 0);

    private char label;
    private Integer rotationAngle;


    public static Command getCommandByLabel(char label) {
        Command command;
        switch (label) {
            case 'G':
                command = Command.L;
                break;
            case 'D':
                command = Command.R;
                break;
            case 'A':
                command = Command.A;
                break;
            default:
                throw new MowerException(MowerError.MOWER_WRONG_COMMAND);
        }
        return command;
    }


}
