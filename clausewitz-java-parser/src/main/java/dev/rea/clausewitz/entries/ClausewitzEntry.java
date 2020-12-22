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

import java.util.Optional;

public abstract class ClausewitzEntry {

    public final String valueOperator;
    public final String name;

    private ClausewitzMapEntry parent;

    protected ClausewitzEntry(String name, String valueOperator) {
        this.name = name;
        this.valueOperator = valueOperator;
    }

    protected ClausewitzEntry(ClausewitzMapEntry parent, String name, String valueOperator) {
        this(name, valueOperator);
        setParent(parent);
    }

    public Optional<ClausewitzMapEntry> getParent() {
        return Optional.ofNullable(parent);
    }

    public void setParent(ClausewitzMapEntry parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %n",
                name, valueOperator, valueToString());
    }

    public int getDepth() {
        int depth = 0;
        ClausewitzEntry temp = parent;
        while (temp != null) {
            temp = temp.parent;
            depth++;
        }
        return depth;
    }

    public abstract String valueToString();

}
