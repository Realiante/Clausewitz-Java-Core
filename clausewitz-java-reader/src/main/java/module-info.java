module clausewitz.java.reader {
    requires clausewitz.java.parser;
    requires transitive clausewitz.java.entries;

    exports dev.rea.clausewitz.reader;
}