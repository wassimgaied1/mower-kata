package fr.sg.mower.kata;

import fr.sg.mower.kata.business.GenericMowerCommand;
import fr.sg.mower.kata.domain.Mower;
import fr.sg.mower.kata.domain.Surface;
import fr.sg.mower.kata.factory.impl.GenericMowerCommandFactoryImpl;
import fr.sg.mower.kata.utils.FileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Wassim Gaied
 */

public class Starter {

    public static Logger logger = LoggerFactory.getLogger(Starter.class);

    public static void main(String[] args) {

        FileParser fileParser = new FileParser(new GenericMowerCommandFactoryImpl());

        Surface surface = fileParser.buildSurface(args[0]);

        for (Mower mower : surface.getMowerList()) {

            for (GenericMowerCommand genericMowerCommand : mower.getGenericMowerCommands()) {

                mower = genericMowerCommand.executeCommand(mower);
            }

            printMowerPosition(mower);
        }
    }

    private static void printMowerPosition(Mower mower) {
        logger.info(mower.getPosition().getX() + " " + mower.getPosition().getY() + " " + mower.getPosition().getOrientation().getLabel());
    }
}
