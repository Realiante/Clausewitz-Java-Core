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

package dev.rea.clausewitz.core.parser;

import dev.rea.clausewitz.core.exceptions.UnexpectedException;
import dev.rea.clausewitz.common.contracts.Entry;
import dev.rea.clausewitz.common.contracts.Format;
import dev.rea.clausewitz.common.contracts.Value;
import dev.rea.clausewitz.common.values.ClausewitzDate;
import dev.rea.clausewitz.common.values.ClausewitzPercent;
import dev.rea.clausewitz.parser.entries.ParsedEntry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

abstract class EntryFormatter {

    protected final Set<Entry> format(ArrayList<ParsedEntry> entries) {
        Set<Entry> entrySet = new HashSet<>();
        entries.forEach(entry -> {
            Object objValue = fetchParser(entry).parse(entry.valueToString());
            //todo when an entry class is added
        });
        return entrySet;
    }

    private Format<?> fetchParser(ParsedEntry entry) {
        switch (entry.valueType) {
            case INT:
                return integerFormat();
            case PERCENT:
                return percentFormat();
            case FLOAT:
                return floatFormat();
            case DATE:
                return dateFormat();
            case STRING:
                return stringFormat();
            case ARRAY:
                return arrayFormat();
            case CLAUSE:
                throw new IllegalArgumentException("Something went wrong, Clauses should not be handled by the entry formatter!");
            default:
        }
        throw new UnexpectedException(String.format("Type currently not supported. For element: %s with value type %s", entry.name, entry.valueType));
    }

    protected abstract Format<Integer> integerFormat();

    protected abstract Format<ClausewitzPercent> percentFormat();

    protected abstract Format<Float> floatFormat();

    protected abstract Format<ClausewitzDate> dateFormat();

    protected abstract Format<String> stringFormat();

    protected abstract Format<ArrayList<Value<?>>> arrayFormat();
}
