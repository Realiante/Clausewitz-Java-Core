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

import dev.rea.clausewitz.entries.ClausewitzParsedEntry;
import dev.rea.clausewitz.interfaces.Result;

import java.util.ArrayList;
import java.util.Optional;

class FileParseResult implements Result<ArrayList<ClausewitzParsedEntry>> {

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
