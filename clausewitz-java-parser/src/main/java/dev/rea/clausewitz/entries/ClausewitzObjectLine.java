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

import dev.rea.clausewitz.datatypes.ClausewitzDataType;
import dev.rea.clausewitz.datatypes.ClausewitzValueOperator;
import dev.rea.clausewitz.datatypes.UnsupportedObjectTypeException;

import java.util.ArrayList;

public class ClausewitzObjectLine extends ClausewitzLine {

    private Object object;
    private ClausewitzDataType type;

    public ClausewitzObjectLine(ClausewitzMapLine parent, String name, ClausewitzValueOperator valueOperator, Object object) {
        super(parent, name, valueOperator);
        setLegalObject(object);
    }

    public ClausewitzObjectLine(String name, ClausewitzValueOperator valueOperator, Object object) {
        super(name, valueOperator);
        setLegalObject(object);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        setLegalObject(object);
    }

    public ClausewitzDataType getType() {
        return type;
    }

    private void setLegalObject(Object object) {
        if (object == null) {
            throw new NullPointerException("Object of an entry cannot be null!");
        }

        ClausewitzDataType newType = ClausewitzDataType.getType(object);
        if (newType == ClausewitzDataType.UNSUPPORTED) {
            throw new UnsupportedObjectTypeException(object.getClass());
        }

        this.type = newType;
        this.object = object;
    }

    @Override
    public String valueToString() {
        if (type == ClausewitzDataType.ARRAY) {
            return arrayToString();
        }

        return object.toString();
    }

    @SuppressWarnings("unchecked")
    private String arrayToString() {
        ArrayList<Object> list = (ArrayList<Object>) object;

        StringBuilder builder = new StringBuilder(" ");
        list.forEach(entry -> builder.append(entry.toString()).append(" "));
        return String.format("{%s}", builder.toString());
    }
}
