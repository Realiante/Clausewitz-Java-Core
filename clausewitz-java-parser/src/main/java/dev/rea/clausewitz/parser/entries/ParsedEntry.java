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

package dev.rea.clausewitz.parser.entries;

import dev.rea.clausewitz.interfaces.val.ValueType;

import java.util.Optional;

public abstract class ParsedEntry {

    public final String valueOperator;
    public final String name;
    public final ValueType valueType;

    private ClauseParsedEntry parent;

    protected ParsedEntry(String name, String valueOperator, ValueType valueType) {
        this.name = name;
        this.valueOperator = valueOperator;
        this.valueType = valueType;
    }

    protected ParsedEntry(ClauseParsedEntry parent, String name, String valueOperator, ValueType valueType) {
        this(name, valueOperator, valueType);
        setParent(parent);
    }

    public Optional<ClauseParsedEntry> getParent() {
        return Optional.ofNullable(parent);
    }

    public void setParent(ClauseParsedEntry parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %n",
                name, valueOperator, valueToString());
    }

    public int getDepth() {
        int depth = 0;
        ParsedEntry temp = parent;
        while (temp != null) {
            temp = temp.parent;
            depth++;
        }
        return depth;
    }

    public abstract String valueToString();

}