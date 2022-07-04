package fr.sg.mower.kata.business;

import fr.sg.mower.kata.domain.Mower;
import fr.sg.mower.kata.domain.Position;
import fr.sg.mower.kata.domain.enums.Command;
import fr.sg.mower.kata.domain.enums.Orientation;

/**
 * @author Wassim Gaied
 */
public class RotationMowerCommand extends GenericMowerCommand {

    public RotationMowerCommand(Position maxPosition, Command command) {
        super(maxPosition, command);
    }

    /**
     * This method aims to calculates the next orientation using trigonometry form.
     */
    @Override
    public Mower executeCommand(Mower mower) {
        Integer targetAngle = mower.getPosition().getOrientation().getAngle() + command.getRotationAngle();
        int x = (int) Math.cos(Math.toRadians(targetAngle));
        int y = (int) Math.sin(Math.toRadians(targetAngle));
        return Mower.builder()
                .position(Position.builder().x(mower.getPosition().getX()).y(mower.getPosition().getY())
                        .orientation(Orientation.getNextOrientation(x, y)).build())
                .genericMowerCommands(mower.getGenericMowerCommands()).build();
    }
}
