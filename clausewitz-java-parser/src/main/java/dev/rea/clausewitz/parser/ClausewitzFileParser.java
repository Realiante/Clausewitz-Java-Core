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
import dev.rea.clausewitz.entries.ClausewitzParsedEntry;
import dev.rea.clausewitz.interfaces.Result;
import dev.rea.clausewitz.parser.listeners.ClausewitzFileListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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
        var pwe = new ExtendedParser(lexer);

        ParseTreeWalker walker = new ParseTreeWalker();
        ClausewitzFileListener listener = new ClausewitzFileListener(lexer);

        walker.walk(listener, pwe.getParser().file());
        ArrayList<String> messages = new ArrayList<>(pwe.getAntlrErrors());
        messages.addAll(listener.getErrors());

        return new FileParseResult(messages, listener.getFileEntriesList());

    }

    private static ClausewitzLexer buildLexer(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        return new ClausewitzLexer(CharStreams.fromStream(inputStream));
    }

    private static ClausewitzLexer buildLexer(String string) {
        return new ClausewitzLexer(CharStreams.fromString(string));
    }

    private static class FileParseResult implements Result<ArrayList<ClausewitzParsedEntry>> {

        private final String message;
        private final ArrayList<ClausewitzParsedEntry> entriesResult;

        public FileParseResult(ArrayList<String> errors, ArrayList<ClausewitzParsedEntry> entriesResult) {
            String err = null;

            if (!errors.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                errors.forEach((s -> stringBuilder.append(s).append("\n")));
                err = stringBuilder.toString();
            }

            this.message = err;
            this.entriesResult = entriesResult;
        }

        @Override
        public Optional<String> getMessage() {
            return Optional.ofNullable(message);
        }

        @Override
        public Optional<ArrayList<ClausewitzParsedEntry>> getResult() {
            return Optional.of(entriesResult);
        }
    }
}
