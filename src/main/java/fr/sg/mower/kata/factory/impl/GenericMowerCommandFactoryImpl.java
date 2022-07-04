package fr.sg.mower.kata.factory.impl;

import fr.sg.mower.kata.business.AdvanceMowerCommand;
import fr.sg.mower.kata.business.GenericMowerCommand;
import fr.sg.mower.kata.business.RotationMowerCommand;
import fr.sg.mower.kata.domain.Position;
import fr.sg.mower.kata.domain.enums.Command;
import fr.sg.mower.kata.domain.enums.Orientation;
import fr.sg.mower.kata.exceptions.MowerError;
import fr.sg.mower.kata.exceptions.MowerException;
import fr.sg.mower.kata.factory.GenericMowerCommandFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Wassim Gaied
 */
@Service
public class GenericMowerCommandFactoryImpl implements GenericMowerCommandFactory {

    public static final String SPACE = " ";
    private static final String regexNumber = "\\d+";

    /**
     * This method aims to create mower generic command
     */
    private static GenericMowerCommand buildGenericMowerCommand(Command command, Position maxPosition) {

        if (command.equals(Command.A)) return new AdvanceMowerCommand(maxPosition, command);


        else return new RotationMowerCommand(maxPosition, command);
    }

    private static Boolean isNumber(String input1, String input2) {
        return (input1 + input2).matches(regexNumber);
    }

    /**
     * This method aims to create list of mower generic command
     */
    @Override
    public List<GenericMowerCommand> createGenericMowerCommand(String lineMoves, Position maxPosition) {
        List<GenericMowerCommand> genericMowerCommandList = new ArrayList<>();
        for (int i = 0; i < lineMoves.length(); i++) {
            Command command = Command.getCommandByLabel(lineMoves.charAt(i));
            genericMowerCommandList.add(buildGenericMowerCommand(command, maxPosition));

        }
        return genericMowerCommandList;
    }

    /**
     * This method aims to Position Object
     */
    @Override
    public Position getPositionAndOptionalOrientation(String position) {
        String[] positionSplitted = position.split(SPACE);

        if (positionSplitted.length == 2 && isNumber(positionSplitted[0], positionSplitted[1]))
            return Position.builder().x(Integer.parseInt(positionSplitted[0])).y(Integer.parseInt(positionSplitted[1])).build();

        else if (positionSplitted.length == 3 && isNumber(positionSplitted[0], positionSplitted[1]))
            return Position.builder().x(Integer.parseInt(positionSplitted[0])).y(Integer.parseInt(positionSplitted[1])).orientation(Orientation.getOrientationByLabel(positionSplitted[2])).build();

        else throw new MowerException(MowerError.MOWER_WRONG_MAX_LENGTH_OR_FORMAT);
    }

}
