package fr.sg.mower.kata.business;

import fr.sg.mower.kata.domain.Mower;
import fr.sg.mower.kata.domain.Position;
import fr.sg.mower.kata.domain.enums.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Wassim Gaied
 */
@AllArgsConstructor
@Data
public abstract class GenericMowerCommand {

    public Position maxPosition;
    public Command command;

    public abstract Mower executeCommand(Mower mower);

}
