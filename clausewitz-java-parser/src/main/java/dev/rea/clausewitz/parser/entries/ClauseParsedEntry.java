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

import java.util.ArrayList;
import java.util.HashMap;

public class ClauseParsedEntry extends ParsedEntry {

    private final ArrayList<ParsedEntry> entries = new ArrayList<>();
    private final HashMap<String, ParsedEntry> childMap = new HashMap<>();

    public ClauseParsedEntry(String name, String valueOperator) {
        super(name, valueOperator, ValueType.CLAUSE);
    }

    public ClauseParsedEntry(ClauseParsedEntry parent, String name, String valueOperator) {
        super(parent, name, valueOperator, ValueType.CLAUSE);
    }

    @Override
    public String valueToString() {
        return String.format("{%n%s%s}", new EntryStringBuilder(entries).build(), spacePrefix(this));
    }

    public void addChild(ParsedEntry entry) {
        childMap.put(entry.name, entry);
        entries.add(entry);
        entry.setParent(this);
    }

    public ParsedEntry getChild(String name) {
        return childMap.get(name);
    }

    public ArrayList<ParsedEntry> getChildren() {
        return new ArrayList<>(entries);
    }

    public void setChildren(ArrayList<ParsedEntry> children) {
        entries.clear();
        childMap.clear();

        for (var entry : children) {
            addChild(entry);
        }
    }

    private String spacePrefix(ParsedEntry entry) {
        return "\t".repeat(entry.getDepth());
    }

    private class EntryStringBuilder {
        final ArrayList<ParsedEntry> entries;

        EntryStringBuilder(ArrayList<ParsedEntry> entries) {
            this.entries = entries;
        }

        private String build() {
            return entriesToString();
        }

        private String entriesToString() {
            StringBuilder builder = new StringBuilder();
            entries.forEach(entry -> builder.append(spacePrefix(entry)).append(entry.toString()));
            return builder.toString();
        }
    }
}
