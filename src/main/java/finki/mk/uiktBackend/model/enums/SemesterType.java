package finki.mk.uiktBackend.model.enums;

import java.util.Arrays;

public enum SemesterType {
    Зимски,
    Летен;

    public static SemesterType getEnumByIndex(int index) {
        return Arrays.stream(SemesterType.values()).toList().stream().filter(x -> x.ordinal() == (index - 1)).findFirst().orElse(null);
    }
}
