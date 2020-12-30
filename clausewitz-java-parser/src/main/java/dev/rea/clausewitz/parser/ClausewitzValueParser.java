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
import dev.rea.clausewitz.interfaces.Result;
import dev.rea.clausewitz.interfaces.val.ValueType;
import dev.rea.clausewitz.parser.listeners.ClausewitzValueListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.ArrayList;
import java.util.Optional;

public class ClausewitzValueParser {

    private ClausewitzValueParser() {
        //static class
    }

    public static Result<ValueType> parse(String text) {
        var lexer = new ClausewitzLexer(CharStreams.fromString(text));
        var pwe = new ExtendedParser(lexer);

        ParseTreeWalker walker = new ParseTreeWalker();
        var listener = new ClausewitzValueListener(lexer);

        walker.walk(listener, pwe.getParser().value());
        ArrayList<String> messages = new ArrayList<>(pwe.getAntlrErrors());
        messages.addAll(listener.getErrors());

        return new ValueTypeResult(messages, listener.getFirstValueType());
    }

    private static class ValueTypeResult implements Result<ValueType> {

        private final String message;
        private final ValueType result;

        ValueTypeResult(ArrayList<String> errors, ValueType result) {
            String err = null;

            if (!errors.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                errors.forEach((s -> stringBuilder.append(s).append("\n")));
                err = stringBuilder.toString();
            }

            this.message = err;
            this.result = result;
        }

        @Override
        public Optional<String> getMessage() {
            return Optional.ofNullable(message);
        }

        @Override
        public Optional<ValueType> getResult() {
            return Optional.of(result);
        }
    }
}
