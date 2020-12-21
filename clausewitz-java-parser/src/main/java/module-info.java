module clausewitz.java.parser {
    requires org.antlr.antlr4.runtime;

    exports dev.rea.clausewitz.entries;
    exports dev.rea.clausewitz.datatypes;

    opens dev.rea.clausewitz.entries to clausewitz.java.interfaces, clausewitz.java.core;
    opens dev.rea.clausewitz.datatypes;
}