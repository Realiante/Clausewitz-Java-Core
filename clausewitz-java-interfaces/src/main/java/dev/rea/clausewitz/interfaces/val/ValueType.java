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

package dev.rea.clausewitz.interfaces.val;

import java.util.List;

@SuppressWarnings("rawtypes") //Rawtypes are only used in one method, should be safe to suppress
public enum ValueType {
    INT("INT", Integer.class),
    PERCENT("PERCENT", ClausewitzPercent.class),
    FLOAT("FLOAT", Float.class),
    DATE("DATE", ClausewitzDate.class),
    STRING("STRING", String.class),
    CLAUSE("CLAUSE", Clause.class),
    ARRAY("ARRAY", List.class);

    private final String typeSymbolic;
    private final Class typeClass;

    ValueType(String typeSymbolic, Class typeClass) {
        this.typeSymbolic = typeSymbolic;
        this.typeClass = typeClass;
    }

    public static ValueType getBySymbolic(String symbolic) {
        for (var valType : ValueType.values()) {
            if (valType.getSymbolic().equals(symbolic)) {
                return valType;
            }
        }
        throw new IllegalArgumentException("No ValueType with " + symbolic + " symbolic name exists!");
    }

    public static ValueType getType(Object object) {
        for (var type : values()) {
            if (type.typeClass.isInstance(object)) {
                return type;
            }
        }
        return null;
    }

    public String getSymbolic() {
        return typeSymbolic;
    }

    @Override
    public String toString() {
        return typeSymbolic;
    }
}
