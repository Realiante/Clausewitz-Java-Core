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

package dev.rea.clausewitz.core.format;

import dev.rea.clausewitz.interfaces.val.Value;
import dev.rea.clausewitz.interfaces.val.ValueType;

import java.util.ArrayList;

public final class ArrayFormat extends AbstractValueFormat<ArrayList<Value>> {

    @Override
    protected ArrayList<Value> buildObject(String string) {
        //TODO: When arrays are added
        throw new UnsupportedOperationException();
    }

    @Override
    public ValueType getValueType() {
        return ValueType.ARRAY;
    }
}
