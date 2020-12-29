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

import dev.rea.clausewitz.ClausewitzLexer;

public enum ValueType {
    INT(ClausewitzLexer.VOCABULARY.getSymbolicName(ClausewitzLexer.INT)),
    PERCENT(ClausewitzLexer.VOCABULARY.getSymbolicName(ClausewitzLexer.PERCENT)),
    FLOAT(ClausewitzLexer.VOCABULARY.getSymbolicName(ClausewitzLexer.FLOAT)),
    DATE(ClausewitzLexer.VOCABULARY.getSymbolicName(ClausewitzLexer.DATE)),
    STRING(ClausewitzLexer.VOCABULARY.getSymbolicName(ClausewitzLexer.STRING)),
    CLAUSE("CLAUSE"),
    ARRAY("ARRAY"),
    ERROR("ERR");

    private final String typeSymbolic;

    ValueType(String typeSymbolic) {
        this.typeSymbolic = typeSymbolic;
    }

    public static ValueType getBySymbolic(String symbolic) {
        for (var valType : ValueType.values()) {
            if (valType.getSymbolic().equals(symbolic)) {
                return valType;
            }
        }
        throw new IllegalArgumentException("No ValueType with " + symbolic + " symbolic name exists!");
    }

    public String getSymbolic() {
        return typeSymbolic;
    }

    @Override
    public String toString() {
        return typeSymbolic;
    }
}
