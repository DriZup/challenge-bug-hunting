package main.enums;


import static java.time.chrono.JapaneseEra.values;

public enum VideoCategory {
        FILME, SERIE, DOCUMENTARIO;

public static boolean isValidCategory(String category) {
    for (VideoCategory c : values()) {
        if (c.name().equalsIgnoreCase(category)) {
            return true;
        }
    }
    return false;
}

public static VideoCategory fromString(String category) {
    for (VideoCategory c : values()) {
        if (c.name().equalsIgnoreCase(category)) {
            return c;
        }
    }
    throw new IllegalArgumentException("Categoria inválida. As categorias válidas são: FILME, SERIE, DOCUMENTARIO.");
}
}

