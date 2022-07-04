package fr.sg.mower.kata.domain;

import fr.sg.mower.kata.business.GenericMowerCommand;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * @author Wassim Gaied
 */
@EqualsAndHashCode
@Getter
@Builder
public final class Mower {

    private final List<GenericMowerCommand> genericMowerCommands;
    private final Position position;

    public Mower(List<GenericMowerCommand> genericMowerCommands, Position position) {
        this.genericMowerCommands = List.copyOf(genericMowerCommands);
        this.position = position;
    }
}
