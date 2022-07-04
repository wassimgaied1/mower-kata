package fr.sg.mower.kata.domain;

import fr.sg.mower.kata.domain.enums.Orientation;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Wassim Gaied
 */
@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
public final class Position {
    private final int x;
    private final int y;
    private final Orientation orientation;
}
