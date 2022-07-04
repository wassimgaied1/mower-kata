package fr.sg.mower.kata.domain;

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
public final class Surface {

    private final List<Mower> mowerList;
    private final Position maxPosition;

    public Surface(List<Mower> mowerList, Position maxPosition) {
        this.mowerList = List.copyOf(mowerList);
        this.maxPosition = maxPosition;
    }
}
