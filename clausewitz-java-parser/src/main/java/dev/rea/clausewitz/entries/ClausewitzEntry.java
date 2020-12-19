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

    private final CEntryNameKey nameKey;

    protected ClausewitzMapEntry parent;
    private ClausewitzValueOperator valueOperator;

    protected ClausewitzEntry(String name, ClausewitzValueOperator valueOperator) {
        this.nameKey = new CEntryNameKey(name);
        this.valueOperator = valueOperator;
    }

    protected ClausewitzEntry(ClausewitzMapEntry parent, String name, ClausewitzValueOperator valueOperator) {
        this(name, valueOperator);
        setParent(parent);
    }

    CEntryNameKey getKey() {
        return nameKey;
    }

    public String getName() {
        return nameKey.getCurrentName();
    }

    public void setName(String newName) {
        nameKey.setNewLegalName(newName);
    }

    public Optional<ClausewitzMapEntry> getParent() {
        return Optional.ofNullable(parent);
    }

    public void setParent(ClausewitzMapEntry parent) {
        if (this.parent != parent) {
            if (parent == null) {
                this.parent.removeChild(this);
            } else if (this.parent == null) {
                parent.addChild(this);
            } else {
                this.parent.removeChild(this);
                parent.addChild(this);
            }

            this.parent = parent;
        }
    }

    public ClausewitzValueOperator getValueOperator() {
        return valueOperator;
    }

    public void setValueOperator(ClausewitzValueOperator valueOperator) {
        this.valueOperator = valueOperator;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %n",
                nameKey.getCurrentName(), valueOperator.toString(), valueToString());
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

    public enum ClausewitzValueOperator {
        EQUAL("="),
        GREATER_OR_EQUAL(">="),
        LESSER_OR_EQUAL("<="),
        GREATER(">"),
        LESSER("<");

        String op;

        ClausewitzValueOperator(String op) {
            this.op = op;
        }

        public static ClausewitzValueOperator get(String operation) {
            for (var operator : values()) {
                if (operator.op.equals(operation)) {
                    return operator;
                }
            }

            throw new IllegalArgumentException();
        }

        @Override
        public String toString() {
            return op;
        }
    }
}
