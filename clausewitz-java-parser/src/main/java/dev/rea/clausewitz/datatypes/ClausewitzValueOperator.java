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

package dev.rea.clausewitz.datatypes;

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
