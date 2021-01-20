module clausewitz.java.core {
    requires transitive clausewitz.java.interfaces;
    requires clausewitz.java.parser;

    exports dev.rea.clausewitz.core.format;
    exports dev.rea.clausewitz.core.values;
    exports dev.rea.clausewitz.core.parser;
}