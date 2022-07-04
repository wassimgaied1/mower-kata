package fr.sg.mower.kata.utils;

import fr.sg.mower.kata.business.GenericMowerCommand;
import fr.sg.mower.kata.business.RotationMowerCommand;
import fr.sg.mower.kata.domain.Position;
import fr.sg.mower.kata.domain.Surface;
import fr.sg.mower.kata.domain.enums.Command;
import fr.sg.mower.kata.domain.enums.Orientation;
import fr.sg.mower.kata.exceptions.MowerException;
import fr.sg.mower.kata.factory.GenericMowerCommandFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.when;

/**
 * @author Wassim Gaied
 */
@RunWith(MockitoJUnitRunner.class)
public class FileParserTest {

    @Mock
    private GenericMowerCommandFactory genericMowerCommandFactory;

    @InjectMocks
    private FileParser fileParser;


    private Position maxPosition;

    private Position firstPosition;

    private Position secondPosition;

    private List<GenericMowerCommand> genericFirstMowerCommandList;

    private List<GenericMowerCommand> genericSecondMowerCommandList;

    @Before
    public void setUp() {

        this.fileParser = new FileParser(genericMowerCommandFactory);
        maxPosition = Position.builder().x(5).y(5).build();
        firstPosition = Position.builder().x(1).y(2).orientation(Orientation.N).build();
        secondPosition = Position.builder().x(3).y(3).orientation(Orientation.E).build();

        genericFirstMowerCommandList = new ArrayList<>();
        genericFirstMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.L));
        genericFirstMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.A));
        genericFirstMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.L));
        genericFirstMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.A));
        genericFirstMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.L));
        genericFirstMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.L));
        genericFirstMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.A));
        genericFirstMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.L));
        genericFirstMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.A));
        genericFirstMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.A));

        genericSecondMowerCommandList = new ArrayList<>();
        genericSecondMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.A));
        genericSecondMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.A));
        genericSecondMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.R));
        genericSecondMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.A));
        genericSecondMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.A));
        genericSecondMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.R));
        genericSecondMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.A));
        genericSecondMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.R));
        genericSecondMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.R));
        genericSecondMowerCommandList.add(new RotationMowerCommand(maxPosition, Command.A));

    }

    @Test(expected = MowerException.class)
    public void testReadLinesCaseWrongFilePath() {
        String path = "wrong/path";
        fileParser.readLines(path);
    }

    @Test
    public void testReadLines() {
        String path = "src/test/resources/mower";
        List<String> lines = fileParser.readLines(path);
        assertTrue(!lines.isEmpty());
    }

    @Test(expected = MowerException.class)
    public void testBuildSurfaceCaseWrongFilePath() {
        String path = "src/test/resources/emptyMowerFile";
        fileParser.buildSurface(path);
    }

    @Test(expected = MowerException.class)
    public void testBuildSurfaceCaseMaxPositionNotRespected() {
        String path = "src/test/resources/mowerMaxPositionNotRespected";
        when(this.genericMowerCommandFactory.getPositionAndOptionalOrientation("5 5 5 7 9 1 2")).thenThrow(MowerException.class);
        fileParser.buildSurface(path);
    }

    @Test(expected = MowerException.class)
    public void testBuildSurfaceCaseWrongFormatForInput() {
        String path = "src/test/resources/mowerInputWrongFormat";
        when(this.genericMowerCommandFactory.getPositionAndOptionalOrientation("5 A")).thenThrow(MowerException.class);

        fileParser.buildSurface(path);
    }

    @Test(expected = MowerException.class)
    public void testBuildSurfaceCaseWrongOrientation() {
        String path = "src/test/resources/mowerWrongOrientation";
        when(this.genericMowerCommandFactory.getPositionAndOptionalOrientation("5 5")).thenReturn(maxPosition);
        when(this.genericMowerCommandFactory.getPositionAndOptionalOrientation("1 2 K")).thenThrow(MowerException.class);
        fileParser.buildSurface(path);
    }

    @Test(expected = MowerException.class)
    public void testBuildSurfaceCaseWrongCommand() {
        String path = "src/test/resources/mowerWrongCommand";
        when(this.genericMowerCommandFactory.getPositionAndOptionalOrientation("5 5")).thenReturn(maxPosition);
        when(this.genericMowerCommandFactory.getPositionAndOptionalOrientation("1 2 N")).thenReturn(firstPosition);
        when(this.genericMowerCommandFactory.createGenericMowerCommand("GMGAGAGAA", maxPosition)).thenThrow(MowerException.class);
        fileParser.buildSurface(path);
    }

    @Test
    public void testBuildSurface() {
        String path = "src/test/resources/mower";
        when(this.genericMowerCommandFactory.getPositionAndOptionalOrientation("5 5")).thenReturn(maxPosition);
        when(this.genericMowerCommandFactory.getPositionAndOptionalOrientation("1 2 N")).thenReturn(firstPosition);
        when(this.genericMowerCommandFactory.createGenericMowerCommand("GAGAGAGAA", maxPosition)).thenReturn(genericFirstMowerCommandList);
        when(this.genericMowerCommandFactory.getPositionAndOptionalOrientation("3 3 E")).thenReturn(firstPosition);
        when(this.genericMowerCommandFactory.createGenericMowerCommand("AADAADADDA", maxPosition)).thenReturn(genericSecondMowerCommandList);

        Surface surface = fileParser.buildSurface(path);

        assertNotNull(surface);
        assertEquals(2, surface.getMowerList().size());
    }
}
