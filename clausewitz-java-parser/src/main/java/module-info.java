module clausewitz.java.parser {
    requires org.antlr.antlr4.runtime;
    requires clausewitz.java.interfaces;

    exports dev.rea.clausewitz.parser.entries to clausewitz.java.core;
    exports dev.rea.clausewitz.parser to clausewitz.java.core;
}