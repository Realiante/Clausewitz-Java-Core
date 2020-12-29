/*
 *  Copyright 2020 Daniel "Realiant" Fedotov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package dev.rea.clausewitz.parser;

import dev.rea.clausewitz.ClausewitzLexer;
import dev.rea.clausewitz.ClausewitzParser;
import dev.rea.clausewitz.entries.ClausewitzParsedEntry;
import dev.rea.clausewitz.interfaces.Result;
import dev.rea.clausewitz.parser.listeners.ClausewitzFileListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ClausewitzFileParser {

    private ClausewitzFileParser() {
        //static class
    }

    public static Result<ArrayList<ClausewitzParsedEntry>> parse(File file) throws IOException {
        return walkAndGetResults(buildLexer(file));
    }

    public static Result<ArrayList<ClausewitzParsedEntry>> parse(String string) {
        return walkAndGetResults(buildLexer(string));
    }

    private static Result<ArrayList<ClausewitzParsedEntry>> walkAndGetResults(ClausewitzLexer lexer) {
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        ClausewitzParser parser = new ClausewitzParser(tokenStream);

        ParseTreeWalker walker = new ParseTreeWalker();
        ClausewitzFileListener listener = new ClausewitzFileListener(lexer);

        walker.walk(listener, parser.file());
        return new FileParseResult(listener.getErrors(), listener.getFileEntriesList());
    }

    private static ClausewitzLexer buildLexer(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        return new ClausewitzLexer(CharStreams.fromStream(inputStream));
    }

    private static ClausewitzLexer buildLexer(String string) {
        return new ClausewitzLexer(CharStreams.fromString(string));
    }
}
