package finki.mk.uiktBackend.model.enums;

import java.util.Arrays;

public enum ExamType {
    Прв,
    Втор,
    Испит;

    public static ExamType getEnumByIndex(int index) {
        return Arrays.stream(ExamType.values()).toList().stream().filter(x -> x.ordinal() == (index - 1)).findFirst().orElse(null);
    }
}
