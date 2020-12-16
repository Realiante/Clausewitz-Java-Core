module clausewitz.java.parser {
    requires clausewitz.java.entries;
    requires org.antlr.antlr4.runtime;

    exports dev.rea.clausewitz.parser to clausewitz.java.reader;
}