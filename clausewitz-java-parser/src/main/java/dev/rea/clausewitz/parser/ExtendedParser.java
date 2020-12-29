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
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;

class ExtendedParser {

    private final ClausewitzParser parser;
    private final ArrayList<String> antlrErrors = new ArrayList<>();

    public ExtendedParser(ClausewitzLexer lexer) {
        lexer.removeErrorListeners();
        lexer.addErrorListener(new CustomLexerErrorListener());

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        parser = new ClausewitzParser(tokenStream);

        parser.removeErrorListeners();
        parser.addErrorListener(new CustomParserErrorListener());

    }

    public ClausewitzParser getParser() {
        return parser;
    }

    public ArrayList<String> getAntlrErrors() {
        return antlrErrors;
    }

    private class CustomParserErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            antlrErrors.add(String.format("line %s:%s %s", line, charPositionInLine, msg));
        }
    }

    private class CustomLexerErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            antlrErrors.add(String.format("line %s:%s %s", line, charPositionInLine, msg));
        }
    }
}
