module clausewitz.java.parser {
    requires org.antlr.antlr4.runtime;
    requires clausewitz.java.common;

    exports dev.rea.clausewitz.parser to clausewitz.java.core;
}