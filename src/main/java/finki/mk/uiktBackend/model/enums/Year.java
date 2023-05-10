package finki.mk.uiktBackend.model.enums;

import java.util.Arrays;

public enum Year {
    Прва,
    Втора,
    Трета,
    Четврта;

    public static Year getEnumByIndex(int index) {
        return Arrays.stream(Year.values()).toList().stream().filter(x -> x.ordinal() == (index - 1)).findFirst().orElse(null);
    }
}
