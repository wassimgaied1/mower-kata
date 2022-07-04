package fr.sg.mower.kata.utils;

import fr.sg.mower.kata.business.GenericMowerCommand;
import fr.sg.mower.kata.domain.Mower;
import fr.sg.mower.kata.domain.Position;
import fr.sg.mower.kata.domain.Surface;
import fr.sg.mower.kata.exceptions.MowerError;
import fr.sg.mower.kata.exceptions.MowerException;
import fr.sg.mower.kata.factory.GenericMowerCommandFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wassim Gaied
 */
public class FileParser {

    private final GenericMowerCommandFactory genericMowerCommandFactory;

    @Autowired
    public FileParser(GenericMowerCommandFactory genericMowerCommandFactory) {
        this.genericMowerCommandFactory = genericMowerCommandFactory;
    }

    /**
     * This method aims to get the lines of file
     */
    public static List<String> readLines(String inputPath) {

        try {
            Path path = Paths.get(inputPath);
            return Files.lines(path).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MowerException(MowerError.MOWER_WRONG_FILE_PATH);
        }

    }

    /**
     * This method aims to get the maximum surface position and the mower list
     */
    public Surface buildSurface(String path) {

        List<String> lines = readLines(path);

        if (lines.size() > 0) {


            Position maxPosition = this.genericMowerCommandFactory.getPositionAndOptionalOrientation(lines.get(0));

            List<Mower> mowerList = new ArrayList<>();

            for (int i = 1; i < lines.size(); i = i + 2) {

                Position startPosition = this.genericMowerCommandFactory.getPositionAndOptionalOrientation(lines.get(i));
                List<GenericMowerCommand> genericMowerCommands = this.genericMowerCommandFactory.createGenericMowerCommand(lines.get(i + 1), maxPosition);
                mowerList.add(Mower.builder().position(startPosition).genericMowerCommands(genericMowerCommands).build());

            }
            return Surface.builder().maxPosition(maxPosition).mowerList(mowerList).build();

        } else throw new MowerException(MowerError.MOWER_EMPTY_FILE);

    }


}
