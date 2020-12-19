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

import java.util.ArrayList;
import java.util.Map;

public enum ClausewitzDataType {
    INTEGER,
    DOUBLE,
    STRING,
    PERCENT,
    DATE,
    MAP,
    ARRAY,
    UNSUPPORTED,
    ANY;

    public static ClausewitzDataType get(String name) {
        switch (name) {
            case "INT":
                return INTEGER;
            case "DOUBLE":
                return DOUBLE;
            case "STRING":
                return STRING;
            case "PERCENT":
                return PERCENT;
            case "DATE":
                return DATE;
            case "MAP":
                return MAP;
            case "ARRAY":
                return ARRAY;
            case "?":
                return ANY;
            default:
                return UNSUPPORTED;
        }
    }

    public static boolean matches(ClausewitzDataType type, Object object) {
        switch (type) {
            case INTEGER:
                return object instanceof Integer;
            case DOUBLE:
                return object instanceof Double;
            case STRING:
                return object instanceof String;
            case PERCENT:
                return object instanceof ClausewitzPercent;
            case DATE:
                return object instanceof ClausewitzDate;
            case MAP:
                return object instanceof Map;
            case ARRAY:
                return object instanceof ArrayList;
            case ANY:
                return true;
        }
        return false;
    }

    public static ClausewitzDataType getType(Object object) {
        for (var dataType : ClausewitzDataType.values()) {
            if (ClausewitzDataType.matches(dataType, object)) {
                return dataType;
            }
        }
        return UNSUPPORTED;
    }
}
