module clausewitz.java.parser {
    requires org.antlr.antlr4.runtime;
    requires transitive clausewitz.java.interfaces;

    exports dev.rea.clausewitz.entries;
    exports dev.rea.clausewitz.datatypes;

    opens dev.rea.clausewitz.entries;
    opens dev.rea.clausewitz.datatypes;
}