package fr.sg.mower.kata.factory;

import fr.sg.mower.kata.business.GenericMowerCommand;
import fr.sg.mower.kata.domain.Position;

import java.util.List;
/**
 * @author Wassim Gaied
 */
public interface GenericMowerCommandFactory {
    List<GenericMowerCommand> createGenericMowerCommand(String lineMoves, Position maxPosition);

    Position getPositionAndOptionalOrientation(String position);
}
