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

import java.util.ArrayList;
import java.util.HashMap;

public class ClausewitzMapEntry extends ClausewitzEntry {

    private final ArrayList<ClausewitzEntry> entries = new ArrayList<>();
    private final HashMap<CEntryNameKey, ClausewitzEntry> childMap = new HashMap<>();

    public ClausewitzMapEntry(String name, ClausewitzValueOperator valueOperator) {
        super(name, valueOperator);
    }

    public ClausewitzMapEntry(ClausewitzMapEntry parent, String name, ClausewitzValueOperator valueOperator) {
        super(parent, name, valueOperator);
    }

    @Override
    public String valueToString() {
        return String.format("{%n%s%s}", new EntryStringBuilder(entries).build(), spacePrefix(this));
    }

    public void addChild(ClausewitzEntry entry) {
        childMap.put(entry.getKey(), entry);
        entries.add(entry);
        entry.parent = this;
    }

    public void removeChild(String name) {
        ClausewitzEntry entry = getChild(name);
        childMap.remove(entry.getKey());
        entry.parent = null;
    }

    public void removeChild(ClausewitzEntry entry) {
        childMap.remove(entry.getKey());
        entry.parent = null;
    }

    public ClausewitzEntry getChild(String name) {
        return childMap.get(CEntryNameKey.findMap.get(name));
    }

    public ArrayList<ClausewitzEntry> getChildren() {
        return new ArrayList<>(entries);
    }

    public void setChildren(ArrayList<ClausewitzEntry> children) {
        entries.clear();
        childMap.clear();

        for (var entry : children) {
            addChild(entry);
        }
    }

    private String spacePrefix(ClausewitzEntry entry) {
        return "\t".repeat(entry.getDepth());
    }

    private class EntryStringBuilder {
        final ArrayList<ClausewitzEntry> entries;

        EntryStringBuilder(ArrayList<ClausewitzEntry> entries) {
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
