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

package dev.rea.clausewitz.common.values;

import dev.rea.clausewitz.common.contracts.Entry;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Clause {

    private final Set<Entry> entries = new HashSet<>();

    public Clause() {

    }

    public Clause(Set<Entry> entries) {
        this.entries.addAll(entries);
    }

    public Clause(Entry... entries) {
        this.entries.addAll(Arrays.asList(entries));
    }

    public Set<Entry> getChildren() {
        return entries;
    }

    public Entry getChild(String name) {
        //TODO: Optimize this: You shouldn't have to search through the set each time you want to retrieve the element.
        for (var entry : entries) {
            if (entry.getName().equals(name)) {
                return entry;
            }
        }

        return null;
    }

    public String getText(int offset) {
        String format = "%s{%s%n}";
        StringBuilder content = new StringBuilder("");
        entries.forEach(entry -> content.append(String.format("%s%n", entry.getText(offset + 1))));
        return String.format(format, offset, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clause clause = (Clause) o;
        return entries.equals(clause.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries);
    }
}
