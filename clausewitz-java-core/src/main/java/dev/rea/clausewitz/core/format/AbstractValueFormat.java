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

package dev.rea.clausewitz.core.format;

import dev.rea.clausewitz.interfaces.Format;
import dev.rea.clausewitz.interfaces.Result;
import dev.rea.clausewitz.interfaces.val.ValueType;

import java.util.Optional;

public abstract class AbstractValueFormat<T> implements Format<T> {

    protected AbstractValueFormat() {
        //does not take any parameters.
    }

    @Override
    public final Result<T> parse(String string) {
        Result<Boolean> matchResultOpt = matchesFormat(string);

        //ignoring isPresent since, boolean result is non-nullable.
        //
        boolean matchResult = matchResultOpt.getResult().get();
        var matchError = matchResultOpt.getMessage();

        if (!matchResult) {
            return new ParseResult(matchError.get()); //boolean result is non-nullable.
        } else {
            return new ParseResult(buildObject(string));
        }
    }

    protected abstract T buildObject(String string);

    @Override
    public final Result<Boolean> matchesFormat(String string) {
        return null;
    }

    public abstract ValueType getValueType();

    private class ParseResult implements Result<T> {

        private final T result;
        private final String error;

        public ParseResult(T result, String error) {
            this.result = result;
            this.error = error;
        }

        public ParseResult(T result) {
            this.result = result;
            this.error = null;
        }

        public ParseResult(String error) {
            this.result = null;
            this.error = error;
        }

        @Override
        public Optional<String> getMessage() {
            return Optional.ofNullable(error);
        }

        @Override
        public Optional<T> getResult() {
            return Optional.ofNullable(result);
        }
    }
}
