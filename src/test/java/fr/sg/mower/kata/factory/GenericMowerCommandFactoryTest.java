package fr.sg.mower.kata.factory;

import fr.sg.mower.kata.domain.Position;
import fr.sg.mower.kata.domain.enums.Orientation;
import fr.sg.mower.kata.exceptions.MowerException;
import fr.sg.mower.kata.factory.impl.GenericMowerCommandFactoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Wassim Gaied
 */
@RunWith(MockitoJUnitRunner.class)
public class GenericMowerCommandFactoryTest {

    @InjectMocks
    private GenericMowerCommandFactoryImpl genericMowerCommandFactory;


    @Test(expected = MowerException.class)
    public void testGetPositionAndOptionalOrientationCaseWrongMaxLength() {
        genericMowerCommandFactory.getPositionAndOptionalOrientation("5 5 5 7 9 1 2");
    }

    @Test(expected = MowerException.class)
    public void testGetPositionAndOptionalOrientationCaseWrongFormat() {
        genericMowerCommandFactory.getPositionAndOptionalOrientation("1 2 K");
    }

    @Test
    public void testGetPositionAndOptionalOrientationCasePosition() {
        Position position = genericMowerCommandFactory.getPositionAndOptionalOrientation("1 2");
        assertNotNull(position);
        assertEquals(1, position.getX());
        assertEquals(2, position.getY());
    }

    @Test
    public void testGetPositionAndOptionalOrientation() {
        Position position = genericMowerCommandFactory.getPositionAndOptionalOrientation("1 2 N");
        assertNotNull(position);
        assertEquals(1, position.getX());
        assertEquals(2, position.getY());
        assertEquals(Orientation.N, position.getOrientation());

    }
}
