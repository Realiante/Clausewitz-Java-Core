module clausewitz.java.parser {
    requires org.antlr.antlr4.runtime;

    exports dev.rea.clausewitz.entries to clausewitz.java.core, clausewitz.java.api;
    exports dev.rea.clausewitz.parser to clausewitz.java.core;
}