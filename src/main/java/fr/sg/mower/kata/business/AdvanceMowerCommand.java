package fr.sg.mower.kata.business;

import fr.sg.mower.kata.domain.Mower;
import fr.sg.mower.kata.domain.Position;
import fr.sg.mower.kata.domain.enums.Command;

/**
 * @author Wassim Gaied
 */
public class AdvanceMowerCommand extends GenericMowerCommand {


    public AdvanceMowerCommand(Position maxPosition, Command command) {
        super(maxPosition, command);
    }

    /**
     * This method aims to update mower position
     */
    @Override
    public Mower executeCommand(Mower mower) {

        if (((mower.getPosition().getX() + mower.getPosition().getOrientation().getX()) <= maxPosition.getX())
                && ((mower.getPosition().getY() + mower.getPosition().getOrientation().getY()) <= maxPosition.getY())) {

            mower = Mower.builder().position(Position.builder()
                    .x(mower.getPosition().getX() + mower.getPosition().getOrientation().getX())
                    .y(mower.getPosition().getY() + mower.getPosition().getOrientation().getY())
                    .orientation(mower.getPosition().getOrientation()).build()).
                    genericMowerCommands(mower.getGenericMowerCommands()).build();
        }
        return mower;

    }
}
