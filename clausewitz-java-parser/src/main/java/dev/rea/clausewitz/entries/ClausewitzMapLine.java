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

package dev.rea.clausewitz.entries;

import dev.rea.clausewitz.datatypes.ClausewitzValueOperator;

import java.util.ArrayList;
import java.util.HashMap;

public class ClausewitzMapLine extends ClausewitzLine {

    private final ArrayList<ClausewitzLine> entries = new ArrayList<>();
    private final HashMap<CEntryNameKey, ClausewitzLine> childMap = new HashMap<>();

    public ClausewitzMapLine(String name, ClausewitzValueOperator valueOperator) {
        super(name, valueOperator);
    }

    public ClausewitzMapLine(ClausewitzMapLine parent, String name, ClausewitzValueOperator valueOperator) {
        super(parent, name, valueOperator);
    }

    @Override
    public String valueToString() {
        return String.format("{%n%s%s}", new EntryStringBuilder(entries).build(), spacePrefix(this));
    }

    public void addChild(ClausewitzLine entry) {
        childMap.put(entry.getKey(), entry);
        entries.add(entry);
        entry.parent = this;
    }

    public void removeChild(String name) {
        ClausewitzLine entry = getChild(name);
        childMap.remove(entry.getKey());
        entry.parent = null;
    }

    public void removeChild(ClausewitzLine entry) {
        childMap.remove(entry.getKey());
        entry.parent = null;
    }

    public ClausewitzLine getChild(String name) {
        return childMap.get(CEntryNameKey.findKeyByName(name));
    }

    public ArrayList<ClausewitzLine> getChildren() {
        return new ArrayList<>(entries);
    }

    public void setChildren(ArrayList<ClausewitzLine> children) {
        entries.clear();
        childMap.clear();

        for (var entry : children) {
            addChild(entry);
        }
    }

    private String spacePrefix(ClausewitzLine entry) {
        return "\t".repeat(entry.getDepth());
    }

    private class EntryStringBuilder {
        final ArrayList<ClausewitzLine> entries;

        EntryStringBuilder(ArrayList<ClausewitzLine> entries) {
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
