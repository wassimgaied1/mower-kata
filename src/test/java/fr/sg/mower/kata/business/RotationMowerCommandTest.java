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
public class RotationMowerCommandTest {

    private RotationMowerCommand rotationMowerCommandR;
    private RotationMowerCommand rotationMowerCommandL;
    private Mower mower;


    @Before
    public void setUp() {
        Position maxPosition = Position.builder().x(5).y(5).build();

        this.rotationMowerCommandR = new RotationMowerCommand(maxPosition, Command.R);
        this.rotationMowerCommandL = new RotationMowerCommand(maxPosition, Command.L);
        List<GenericMowerCommand> genericMowerCommands = new ArrayList<>();
        genericMowerCommands.add(this.rotationMowerCommandL);
        this.mower = Mower.builder()
                .position(Position.builder().x(1).y(2)
                        .orientation(Orientation.N).build())
                .genericMowerCommands(genericMowerCommands)
                .build();
    }


    @Test
    public void testNextOrientationWIfRotationMowerCommandL() {
        this.mower = this.rotationMowerCommandL.executeCommand(this.mower);
        assertEquals(Orientation.W, this.mower.getPosition().getOrientation());
    }

    @Test
    public void testNextOrientationSIfRotationMowerCommandL() {
        this.mower = this.updateMowerOrientation(Orientation.W);
        this.mower = this.rotationMowerCommandL.executeCommand(this.mower);
        assertEquals(Orientation.S, this.mower.getPosition().getOrientation());
    }

    @Test
    public void testNextOrientationEIfRotationMowerCommandL() {
        this.mower = this.updateMowerOrientation(Orientation.S);
        this.mower = this.rotationMowerCommandL.executeCommand(this.mower);
        assertEquals(Orientation.E, this.mower.getPosition().getOrientation());
    }

    @Test
    public void testNextOrientationNIfRotationMowerCommandL() {
        this.mower = this.updateMowerOrientation(Orientation.E);
        this.mower = this.rotationMowerCommandL.executeCommand(this.mower);
        assertEquals(Orientation.N, this.mower.getPosition().getOrientation());
    }

    @Test
    public void testNextOrientationEIfRotationMowerCommandR() {
        this.mower = this.updateMowerOrientation((Orientation.N));
        this.mower = this.rotationMowerCommandR.executeCommand(this.mower);
        assertEquals(Orientation.E, this.mower.getPosition().getOrientation());
    }

    @Test
    public void testNextOrientationSIfRotationMowerCommandR() {
        this.mower = this.updateMowerOrientation(Orientation.E);
        this.mower = this.rotationMowerCommandR.executeCommand(this.mower);
        assertEquals(Orientation.S, this.mower.getPosition().getOrientation());
    }

    @Test
    public void testNextOrientationWIfRotationMowerCommandR() {
        this.mower = this.updateMowerOrientation(Orientation.S);
        this.mower = this.rotationMowerCommandR.executeCommand(this.mower);
        assertEquals(Orientation.W, this.mower.getPosition().getOrientation());
    }

    @Test
    public void testNextOrientationNIfRotationMowerCommandR() {
        this.mower = this.updateMowerOrientation(Orientation.W);
        this.mower = this.rotationMowerCommandR.executeCommand(this.mower);
        assertEquals(Orientation.N, this.mower.getPosition().getOrientation());
    }

    private Mower updateMowerOrientation(Orientation orientation) {
        return Mower.builder()
                .position(Position.builder().x(mower.getPosition().getX()).y(mower.getPosition().getY())
                        .orientation(orientation).build())
                .genericMowerCommands(mower.getGenericMowerCommands()).build();
    }
}
