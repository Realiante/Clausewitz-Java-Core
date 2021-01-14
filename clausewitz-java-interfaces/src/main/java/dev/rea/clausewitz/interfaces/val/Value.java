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

public final class Value {

    private Object object;
    private ValueType type;

    public Object getValue() {
        return object;
    }

    public void setValue(Object value) {
        var newType = ValueType.getType(object);
        if (newType == null) {
            throw new IllegalArgumentException("Attempting to set value to an unsupported type: " + object.getClass());
        }
        object = value;
        type = newType;
    }

    public ValueType getType() {
        return type;
    }
}
