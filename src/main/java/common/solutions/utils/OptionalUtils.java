package common.solutions.utils;

import java.util.Optional;
import java.util.OptionalInt;

public final class OptionalUtils {

    public OptionalUtils() {
    }

    public static Optional<Integer> valueOF(OptionalInt optionalInt) {
        return optionalInt.isPresent() ? Optional.of(optionalInt.getAsInt()) : Optional.empty();
    }
}
