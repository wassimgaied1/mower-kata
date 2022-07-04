package fr.sg.mower.kata.business;

import fr.sg.mower.kata.domain.Mower;
import fr.sg.mower.kata.domain.Position;
import fr.sg.mower.kata.domain.enums.Command;
import fr.sg.mower.kata.domain.enums.Orientation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Wassim Gaied
 */
public class AdvanceMowerCommandTest {

    private AdvanceMowerCommand advanceMowerCommand;
    private Mower mower;


    @Before
    public void setUp() {
        Position maxPosition = Position.builder().x(5).y(5).build();

        this.advanceMowerCommand = new AdvanceMowerCommand(maxPosition, Command.A);
        List<GenericMowerCommand> genericMowerCommands = new ArrayList<>();
        genericMowerCommands.add(this.advanceMowerCommand);
        this.mower = Mower.builder()
                .position(Position.builder().x(1).y(2)
                        .orientation(Orientation.N).build())
                .genericMowerCommands(genericMowerCommands)
                .build();
    }

    @Test
    public void testAdvanceIfN() {

        this.mower = this.advanceMowerCommand.executeCommand(this.mower);
        assertEquals(1, this.mower.getPosition().getX());
        assertEquals(3, this.mower.getPosition().getY());

    }

    @Test
    public void testAdvanceIfW() {

        this.mower = this.updateMowerPosition(Position.builder().x(1).y(2).orientation(Orientation.W).build());
        this.mower = this.advanceMowerCommand.executeCommand(this.mower);
        assertEquals(0, this.mower.getPosition().getX());
        assertEquals(2, this.mower.getPosition().getY());

    }

    @Test
    public void testAdvanceIfS() {

        this.mower = this.updateMowerPosition(Position.builder().x(1).y(2).orientation(Orientation.S).build());
        this.mower = this.advanceMowerCommand.executeCommand(this.mower);
        assertEquals(1, this.mower.getPosition().getX());
        assertEquals(1, this.mower.getPosition().getY());

    }

    @Test
    public void testAdvanceIfE() {

        this.mower = this.updateMowerPosition(Position.builder().x(1).y(2).orientation(Orientation.E).build());
        this.mower = this.advanceMowerCommand.executeCommand(this.mower);
        assertEquals(2, this.mower.getPosition().getX());
        assertEquals(2, this.mower.getPosition().getY());

    }

    @Test
    public void testNotAdvanceIfMowerInMaxPosition() {

        this.mower = this.updateMowerPosition(Position.builder().x(5).y(5).orientation(Orientation.N).build());
        this.mower = this.advanceMowerCommand.executeCommand(this.mower);
        assertEquals(5, this.mower.getPosition().getX());
        assertEquals(5, this.mower.getPosition().getY());

    }

    private Mower updateMowerPosition(Position position) {
        return Mower.builder()
                .position(position)
                .genericMowerCommands(this.mower.getGenericMowerCommands()).build();

    }
}
